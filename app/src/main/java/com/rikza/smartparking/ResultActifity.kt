package com.rikza.smartparking

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val ringkasan = findViewById<TextView>(R.id.tvRingkasan)
        val totalView = findViewById<TextView>(R.id.tvTotal)

        // PENJELASAN: Mengambil data String dan Int yang dikirim dari MainActivity melalui Intent dengan nilai bawaan yang aman bila data tidak ditemukan
        val satu: String = intent.getStringExtra("EXTRA_SATU") ?: ""
        val dua: String = intent.getStringExtra("EXTRA_DUA") ?: ""
        val jumlah: Int = intent.getIntExtra("EXTRA_JUMLAH", 0)
        val total: Int = intent.getIntExtra("EXTRA_TOTAL", 0)

        // PENJELASAN: Menampilkan hasil output ringkasan dan total biaya parkir dengan format sesuai contoh tampilan (misal: AB1574BY | Innova | Jumlah: 3 dan Total: Rp15000), mengonversi Int ke teks hanya saat ditampilkan di UI
        ringkasan.text = getString(R.string.format_ringkasan, satu, dua, jumlah)
        totalView.text = getString(R.string.format_total, total)
    }
}