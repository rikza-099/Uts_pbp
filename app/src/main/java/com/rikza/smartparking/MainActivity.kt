package com.rikza.smartparking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val satu = findViewById<EditText>(R.id.etSatu)
        val dua = findViewById<EditText>(R.id.etDua)
        val jumlahView = findViewById<EditText>(R.id.etJumlah)
        val tombol = findViewById<Button>(R.id.btnHitung)

        // PENJELASAN: Alur klik tombol menghitung membaca input teks, memvalidasi isian dan rentang jam, menghitung total biaya parkir bertipe Int, lalu mengirim data ke ResultActivity lewat Intent
        tombol.setOnClickListener {
            val teksSatu = satu.text.toString().trim()
            val teksDua = dua.text.toString().trim()

            // PENJELASAN: Mengonversi teks durasi parkir ke Int? secara aman menggunakan toIntOrNull() agar input kosong atau non-angka menghasilkan null dan tidak membuat aplikasi crash
            val jumlah: Int? = jumlahView.text.toString().trim().toIntOrNull()

            // PENJELASAN: Validasi wajib isi untuk nomor polisi dan jenis kendaraan; jika kosong, tampilkan pesan error di field terkait dan hentikan proses dengan return agar tidak lanjut ke perhitungan atau pindah halaman
            if (teksSatu.isEmpty()) {
                satu.error = getString(R.string.error_wajib_diisi)
                satu.requestFocus()
                return@setOnClickListener
            }

            if (teksDua.isEmpty()) {
                dua.error = getString(R.string.error_wajib_diisi)
                dua.requestFocus()
                return@setOnClickListener
            }

            // PENJELASAN: Validasi durasi parkir harus berupa angka bulat dalam rentang 1 sampai 10 jam (termasuk menolak 0, 11, 15, atau input kosong/tidak valid) lalu menampilkan error dan menghentikan proses jika tidak sesuai
            if (jumlah == null || jumlah !in 1..10) {
                jumlahView.error = getString(R.string.error_rentang_jam)
                jumlahView.requestFocus()
                return@setOnClickListener
            }

            val tarif: Int = 5000

            // PERBAIKAN: Menghitung total biaya parkir dengan perkalian bilangan bulat (Int) antara durasi jam dan tarif tetap Rp5.000
            // PENJELASAN: Operasi perkalian Int antara jumlah jam dan tarif 5000 menghasilkan total biaya parkir bertipe Int (contoh: 3 jam * 5000 = 15000)
            val total: Int = jumlah * tarif

            // PENJELASAN: Membuat Explicit Intent untuk membuka ResultActivity dan mengirimkan data nomor polisi, jenis kendaraan, durasi (Int), serta total biaya (Int) menggunakan putExtra
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("EXTRA_SATU", teksSatu)
            intent.putExtra("EXTRA_DUA", teksDua)
            intent.putExtra("EXTRA_JUMLAH", jumlah)
            intent.putExtra("EXTRA_TOTAL", total)
            startActivity(intent)
        }
    }
}