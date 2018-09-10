package com.babynames.gender.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.babynames.core.presentation.getApplicationComponent
import com.babynames.gender.R
import com.babynames.gender.presentation.components.DaggerGenderComponent
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

    private var genderSelection: Boolean = false

    private val GENDER_SELECTED = "gender"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)
        this.genderComponent.inject(this)
        this.text_gender_boy.setOnClickListener(this)
        this.text_gender_girl.setOnClickListener(this)
        this.text_gender_surprise.setOnClickListener(this)

        genderSelection = intent.getBooleanExtra("isGender", false)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_gender_boy -> {
                sharedPreferencesManager.setSharedPreference(GENDER_SELECTED, "male")
                if (!genderSelection) {
                    //startActivity(Intent(this, MainActivity::class.java))
                }

                finish()
            }
            R.id.text_gender_girl -> {
                sharedPreferencesManager.setSharedPreference(GENDER_SELECTED, "female")

                if (!genderSelection) {
                    //startActivity(Intent(this, MainActivity::class.java))
                }

                finish()
            }
            R.id.text_gender_surprise -> {
                sharedPreferencesManager.setSharedPreference(GENDER_SELECTED, "unisex")

                if (!genderSelection) {
                    //startActivity(Intent(this, MainActivity::class.java))
                }

                finish()
            }
        }
    }
}
