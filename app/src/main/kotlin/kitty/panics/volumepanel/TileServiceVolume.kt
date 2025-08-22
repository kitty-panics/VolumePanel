package kitty.panics.volumepanel

import android.app.Dialog
import android.app.PendingIntent
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.service.quicksettings.TileService
import androidx.core.service.quicksettings.PendingIntentActivityWrapper
import androidx.core.service.quicksettings.TileServiceCompat

class TileServiceVolume : TileService() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onClick() {
        super.onClick()

        // 在某些系统中 (e.g. 一加 13-ColorOS 15.0-A15)，startActivityAndCollapse 无法正确折叠 "快速设置"，
        // 转而使用 showDialog 弹出一个对话框，这应该能实现强制折叠。
        // Link: https://developer.android.com/reference/android/service/quicksettings/TileService
        val dialog = Dialog(applicationContext)
        showDialog(dialog)
        dialog.dismiss()
        // 在某些系统中，处于 "快速设置" 界面无法调出音量面板，启用 "动画程序时长缩放" 时也将延长在 "快速设置"
        // 界面的时间，等待 500 毫秒让动画完全过渡。
        handler.postDelayed({
            collapseQSPanel()
        }, 500) // TODO: 根据缩放因子选择不同的时长。
    }

    private fun collapseQSPanel() {
        Intent(this, MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            TileServiceCompat.startActivityAndCollapse(
                this@TileServiceVolume,
                PendingIntentActivityWrapper(
                    this@TileServiceVolume,
                    0,
                    it,
                    PendingIntent.FLAG_UPDATE_CURRENT,
                    true
                )
            )
        }
    }
}
