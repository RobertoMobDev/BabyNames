package com.babynames.babynames

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.babynames.babynames.presentation.components.DaggerGenderComponent
import com.babynames.core.presentation.getApplicationComponent
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_gender.*
import javax.inject.Inject

class GenderActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private val genderComponent by lazy {
        DaggerGenderComponent.builder()
                .applicationComponent(this.getApplicationComponent())
                .build()
    }

    private val GENDER_SELECTED = "gender"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)
        this.genderComponent.inject(this)
        this.text_gender_boy.setOnClickListener(this)
        this.text_gender_girl.setOnClickListener(this)
        this.text_gender_surprise.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_gender_boy -> {
                sharedPreferencesManager.setStringPreference(GENDER_SELECTED, "boy")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.text_gender_girl -> {
                sharedPreferencesManager.setStringPreference(GENDER_SELECTED, "girl")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.text_gender_surprise -> {
                sharedPreferencesManager.setStringPreference(GENDER_SELECTED, "surprise")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
