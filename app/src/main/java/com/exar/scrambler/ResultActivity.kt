package com.exar.scrambler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.net.toUri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOError
import java.io.IOException

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imagePath = intent.getStringExtra("IMAGE").toString();

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        var rawButton: Button = findViewById(R.id.rawButton);
        rawButton.setOnClickListener {
//            TODO: RAW
            val letters = findViewById<EditText>(R.id.raw).text.toString();
            val intent = Intent(this@ResultActivity, SuggestionActivity::class.java).apply {
                putExtra("LETTERS", letters);
            }
            startActivity(intent);
        }
        var filterButton: Button = findViewById(R.id.filterButton);

        filterButton.setOnClickListener {
//            TODO: FILTERED
            val letters = findViewById<EditText>(R.id.filtered).text.toString();
            val intent = Intent(this@ResultActivity, SuggestionActivity::class.java).apply {
                putExtra("LETTERS", letters);
            }
            startActivity(intent);
        }

        val image: InputImage
        try {
            image = InputImage.fromFilePath(this, imagePath.toUri())
            val result = recognizer.process(image).addOnSuccessListener { visionText ->
                //Task success.
                val resultText = visionText.text
                val rawView: TextView = findViewById(R.id.raw)
                rawView.text = resultText
                val filtered = resultText.filter(Char::isUpperCase);
                val filterView: TextView = findViewById(R.id.filtered)
                filterView.text = filtered
                for (block in visionText.textBlocks) {
                    val blockText = block.text
                    val blockCornerPoints = block.cornerPoints
                    val blockFrame = block.boundingBox
                    for (line in block.lines) {
                        val lineText = line.text
                        val lineCornerPoints = line.cornerPoints
                        val lineFrame = line.boundingBox
                        for (element in line.elements) {
                            val elementText = element.text
                            val elementCornerPoints = element.cornerPoints
                            val elementFrame = element.boundingBox
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                //Task failed
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}