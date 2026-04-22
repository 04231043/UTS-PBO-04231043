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
        // Kelanjutan dari Class Mahasiswa
    // Method Transaksi dengan Aturan Bisnis
    fun beli(menu: Menu, inputPin: String) {
        println("\n--- Memproses pesanan ${menu.nama} oleh $nama ---")
        
        // 1. Validasi PIN
        if (inputPin != pin) {
            println("❌ GAGAL: PIN yang Anda masukkan salah!")
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
    // Instansiasi Objek
    val menuGeprek = Menu("Ayam Geprek Razita", 15000.0, 1)
    val jhosua = Mahasiswa("Jhosua", "123456", 20000.0)

    println("=== SIMULASI E-WALLET KANTINPAY ITK ===")

    // Skenario 1: Transaksi Gagal karena PIN salah
    jhosua.beli(menuGeprek, "000000")

    // Skenario 2: Transaksi Sukses (PIN benar, Saldo 20k, Stok 1)
    jhosua.beli(menuGeprek, "123456")

    // Skenario 3: Transaksi Gagal karena Stok habis dan Saldo kurang
    // (Stok jadi 0 setelah dibeli, dan sisa saldo Jhosua tinggal 5000)
    jhosua.beli(menuGeprek, "123456")
}