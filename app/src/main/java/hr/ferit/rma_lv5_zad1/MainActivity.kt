package hr.ferit.rma_lv5_zad1

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hr.ferit.rma_lv5_zad1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var soundPool: SoundPool
    private var isLoaded = false
    var sounds: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.loadSounds()
        this.binding.imgBtnFirst.setOnClickListener { this.playSound(R.raw.bells) }
        this.binding.imgBtnSecond.setOnClickListener { this.playSound(R.raw.car) }
        this.binding.imgBtnThird.setOnClickListener { this.playSound(R.raw.train) }
        setContentView(binding.root)
    }

    private fun loadSounds() {
        this.soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        this.soundPool.setOnLoadCompleteListener { _, _, _ ->
            Toast.makeText(this, "Loaded!", Toast.LENGTH_SHORT).show()
            this.isLoaded = true
        }
        this.sounds[R.raw.bells] = this.soundPool.load(this, R.raw.bells, 1)
        this.sounds[R.raw.car] = this.soundPool.load(this, R.raw.car, 1)
        this.sounds[R.raw.train] = this.soundPool.load(this, R.raw.train, 1)

    }

    private fun playSound(id: Int) {
        if (!this.isLoaded) {
            Toast.makeText(this, "Not loaded!", Toast.LENGTH_SHORT).show()
            return
        }
        val soundID = this.sounds[id] ?: 0
        this.soundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
}