// Entitas Menu
class Menu(val nama: String, val harga: Double, stokAwal: Int) {
    // Data Hiding: Stok tidak bisa diubah sembarangan dari luar
    var stok: Int = stokAwal
        private set 

    fun kurangiStok() {
        if (stok > 0) { stok-- }
    }
}

// Entitas Mahasiswa
class Mahasiswa(val nama: String, private val pin: String, saldoAwal: Double) {
    // Data Hiding: Saldo hanya bisa dibaca, tidak bisa ditimpa sembarangan
    var saldo: Double = saldoAwal
        private set
        
    // Method Transaksi dengan Aturan Bisnis
    fun beli(menu: Menu, inputPin: String) {
        println("\n--- Memproses pesanan ${menu.nama} oleh $nama ---")
        
        // 1. Validasi PIN
        if (inputPin != pin) {
            println("❌ GAGAL: PIN yang Anda masukkan salah! Akses ditolak.")
            return
        }
        // 2. Validasi Ketersediaan Stok
        if (menu.stok <= 0) {
            println("❌ GAGAL: Maaf, stok ${menu.nama} sudah habis.")
            return
        }
        // 3. Validasi Kecukupan Saldo
        if (this.saldo < menu.harga) {
            println("❌ GAGAL: Saldo tidak mencukupi. (Harga: Rp${menu.harga} | Saldo: Rp${this.saldo})")
            return
        }

        // Eksekusi Transaksi (Jika semua validasi lolos)
        this.saldo -= menu.harga
        menu.kurangiStok()
        println("✅ SUKSES: Pembelian ${menu.nama} berhasil!")
        println("   Sisa Saldo $nama: Rp${this.saldo} | Sisa Stok ${menu.nama}: ${menu.stok}")
    }
}

// Fungsi Utama untuk Simulasi
fun main() {
    // Instansiasi Objek (Stok dibanyakin, Saldo dibanyakin)
    val menuGeprek = Menu("Ayam Geprek Razita", 15000.0, 5)
    val menuEsTeh = Menu("Es Teh Manis", 5000.0, 10)
    val jhosua = Mahasiswa("Jhosua", "123456", 500000.0)

    println("=== SIMULASI E-WALLET KANTINPAY ITK ===")

    // Skenario 1: Beli Makan (SUKSES)
    jhosua.beli(menuGeprek, "123456")

    // Skenario 2: Beli Minum (SUKSES)
    jhosua.beli(menuEsTeh, "123456")

    // Skenario 3: Beli Geprek lagi buat dibungkus (SUKSES)
    jhosua.beli(menuGeprek, "123456")

    // Skenario 4: Coba dibobol orang pakai PIN ngasal (GAGAL)
    jhosua.beli(menuEsTeh, "000000")

    // Skenario 5: Coba beli Geprek lagi tapi stok udah tinggal 3 (SUKSES)
    jhosua.beli(menuGeprek, "123456") 

    // Skenario 6: Coba beli Geprek lagi tapi stok udah habis (GAGAL)
    jhosua.beli(menuGeprek, "123456")  
    jhosua.beli(menuGeprek, "123456")  
    jhosua.beli(menuGeprek, "123456")  
}