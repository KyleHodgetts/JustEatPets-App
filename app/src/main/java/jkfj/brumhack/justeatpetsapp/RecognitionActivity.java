package jkfj.brumhack.justeatpetsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.clarifai.api.exception.ClarifaiException;

import java.io.ByteArrayOutputStream;


/**
 * A simple Activity that performs recognition using the Clarifai API.
 */
public class RecognitionActivity extends Activity {
    private static final String TAG = "Recognition_Activty";
    private static final String FILTER = "filter";
    private static final String APP_ID = Credentials.APP_ID;
    private static final String APP_SECRET = Credentials.APP_SECRET;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private final ClarifaiClient client = new ClarifaiClient(APP_ID, APP_SECRET);
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition);

        imageView = (ImageView) findViewById(R.id.imageView);

        dispatchTakePictureIntent();
    }

    /**
     * Launches a camera activity for the user to take a photograph
     */
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Receives the image from the photo activity, and performs all relevant tasks
     *
     * @param requestCode something
     * @param resultCode  something else
     * @param data        the image data, within an intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //get the taken photo as a bitmap
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            if (bitmap != null) {

                //set the image view with the picture that shows whilst the image is being processed.
                imageView.setImageBitmap(bitmap);

                // Run recognition on a background thread since it makes a network call.
                new AsyncTask<Bitmap, Void, RecognitionResult>() {
                    @Override
                    protected RecognitionResult doInBackground(Bitmap... bitmaps) {
                        return recognizeBitmap(bitmaps[0]);
                    }

                    @Override
                    protected void onPostExecute(RecognitionResult result) {
                        filterTagsToGetPet(result);
                    }
                }.execute(bitmap);
            } else {
                Log.i(TAG, "Unable to load image");
                this.finish();
            }
        } else {
            this.finish();
        }
    }

    /**
     * Sends the given bitmap to Clarifai for recognition and returns the result.
     */
    private RecognitionResult recognizeBitmap(Bitmap bitmap) {
        try {
            // Scale down the image for quicker response over network
            int pixelsWide = 320;
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap,
                    pixelsWide, pixelsWide * bitmap.getHeight() / bitmap.getWidth(), true);

            // Compress the image as a JPEG.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            scaled.compress(Bitmap.CompressFormat.JPEG, 90, out);
            byte[] jpeg = out.toByteArray();

            // Send the JPEG to Clarifai and return the result.
            Log.i(TAG, "Waiting for clarifai");
            return client.recognize(new RecognitionRequest(jpeg)).get(0);
        } catch (ClarifaiException e) {
            //Things went wrong. Usually a network failure.
            Log.e(TAG, "Clarifai error" + e);
            e.printStackTrace();
            this.finish(); //TODO: not make this finish(), deal with the error where recognizeBitmap() is called
            return null;
        }
    }


    /**
     * Gets the first animal tag from Clarifai, and starts the food list activity with that filter.
     *
     * @param result result that clarifai returned
     */
    private void filterTagsToGetPet(RecognitionResult result) {
        String animalFilter = "dog"; //everyone likes dogs, not sure of behaviour if I make this blank

        //if given result is ok, filter tags to get animal
        if (result != null && result.getStatusCode() == RecognitionResult.StatusCode.OK) {
            label:
            for (Tag tag : result.getTags()) {

                //gets first tag that matches
                String tagName = tag.getName();
                switch (tagName) {
                    case "cat":
                        animalFilter = "Cat";
                        break label;
                    case "dog":
                        animalFilter = "Dog";
                        break label;
                    case "fish":
                        animalFilter = "Fish";
                        break label;
                }
            }

            toast(animalFilter);

            //send intent to the food api business
            Intent i = new Intent(RecognitionActivity.this, ProductsActivity.class);
            i.putExtra(FILTER, animalFilter.toLowerCase());
            startActivity(i);
            this.finish();
        }
    }

    /**
     * Tells the user what animal was selected, via a toast
     *
     * @param text the animal chosen
     */
    private void toast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
