package wanchuda.test.testlibs.extension

import android.widget.SeekBar

fun SeekBar.onSeekBarChangeListener(init: _SeekBar_OnSeekBarChangeListener.() -> Unit) {
    val listener = _SeekBar_OnSeekBarChangeListener()
    listener.init()
    setOnSeekBarChangeListener(listener)
}

//private stuff
class _SeekBar_OnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {

    private var onProgressChanged: ((SeekBar, Int, Boolean) -> Unit)? = null
    private var onStartTrackingTouch: ((SeekBar) -> Unit)? = null
    private var onStopTrackingTouch: ((SeekBar) -> Unit)? = null

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        onProgressChanged?.invoke(seekBar, progress, fromUser)
    }

    //proxy method
    fun onProgressChanged(listener: (SeekBar, Int, Boolean) -> Unit) {
        onProgressChanged = listener
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        onStartTrackingTouch?.invoke(seekBar)
    }

    //proxy method
    fun onStartTrackingTouch(listener: (SeekBar) -> Unit) {
        onStartTrackingTouch = listener
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        onStopTrackingTouch?.invoke(seekBar)
    }

    //proxy method
    fun onStopTrackingTouch(listener: (SeekBar) -> Unit) {
        onStopTrackingTouch = listener
    }

}