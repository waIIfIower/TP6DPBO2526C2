# TP6DPBO2526C2
Saya Muhammad Dzaka Indrianto dengan NIM 2508755 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
                      ┌──────────────┐
                      │   App.java   │
                      └──────┬───────┘
                             │ (Inisialisasi)
                             ▼
    ┌─────────────────────────────────────────────────┐
    │                    View.java                    │
    │   - Mengubah tampilan UI (repaint)              │
    │   - Menangkap input keyboard dari pemain        │
    └───────────────┬─────────────────▲───────────────┘
                    │                 │
              (Kirim input)  (Render ulang state)
                    │                 │
                    ▼                 │
    ┌─────────────────────────────────┴───────────────┐
    │                   Logic.java                    │
    │   - Game Loop & State Manager                   │
    │   - Kalkulasi Posisi & Collision Detection      │
    └───────┬─────────────────┬────────────────┬──────┘
            │                 │                │
            ▼                 ▼                ▼
    ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
    │  Player.java │  │  Pipe.java   │  │  Sound.java  │
    └──────────────┘  └──────────────┘  └──────────────┘

# Penjelasan Desain(alur)
## Class App.java

Deskripsi: Merupakan titik awal masuknya program (entry point). Berfungsi untuk membuat jendela aplikasi (JFrame), menginisialisasi komponen utama, serta memulai jalannya permainan.

Fungsi / Method Utama:

main(String[] args): Menginstansiasi objek Logic dan View, mengatur konfigurasi jendela GUI (ukuran, judul, opsi penutupan aplikasi), dan memanggil metode untuk memulai permainan.

## Class Logic.java

Deskripsi: Bertindak sebagai pengontrol utama seluruh logika permainan (game logic engine), pengatur kondisi layar (state manager: Menu, Playing, Game Over), perhitung kalkulasi skor, serta deteksi benturan (collision detection).

Fungsi / Method Utama:

run() / update(): Memperbarui koordinat posisi karakter dan rintangan secara berkala pada setiap siklus game loop.

checkCollision(): Memeriksa interaksi benturan antara batas area karakter (Player) dengan rintangan (Pipe) atau batas layar.

spawnPipe(): Membuat dan menambahkan objek rintangan pipa baru secara berkala ke dalam daftar (list).

addScore() / resetGame(): Menambahkan poin ketika pemain berhasil melewati pipa, serta mereset status variabel saat sesi permainan baru dimulai kembali.

## Class View.java

Deskripsi: Merupakan komponen antarmuka pengguna berbasis Java Swing (JPanel). Bertanggung jawab untuk merender visual permainan ke layar berdasarkan kondisi (state) yang dikirimkan oleh Logic, serta menerima masukan tombol dari pemain (KeyListener).

Fungsi / Method Utama:

paintComponent(Graphics g): Menggambar seluruh aset grafis dan tampilan antarmuka ke layar secara real-time.

drawMenu(Graphics g): Merender visual antarmuka layar awal (menu.png).

drawPlay(Graphics g): Merender visual karakter, rintangan pipa, latar belakang, dan skor saat sesi permainan aktif (score.png).

drawGameOver(Graphics g): Merender visual antarmuka saat kondisi kekalahan tercapai (gameOver.png).

keyPressed(KeyEvent e): Menangkap masukan tombol keyboard (seperti tombol Spasi atau Tombol Panah) untuk memicu aksi lompat atau navigasi menu.

## Class Player.java

Deskripsi: Representasi dari karakter utama dalam permainan. Menyimpan data posisi, ukuran, batas area benturan (hitbox), serta memperhitungkan efek fisik sederhana seperti gravitasi dan kecepatan lompatan.

Fungsi / Method Utama:

jump(): Mengubah vektor kecepatan vertikal karakter ke atas saat pemain menekan tombol aksi.

update(): Memperbarui titik koordinat Y karakter akibat pengaruh percepatan gravitasi pada setiap frame.

getBounds(): Mengembalikan area pembatas fisik karakter berbentuk Rectangle untuk keperluan deteksi benturan (collision detection).

reset(): Mengembalikan posisi awal dan kecepatan karakter ketika permainan diulang.

## Class Pipe.java

Deskripsi: Representasi entitas rintangan pipa yang bergerak melintasi layar dari kanan ke kiri. Menyimpan posisi koordinat, jarak celah (gap), serta status apakah pipa tersebut sudah berhasil dilewati oleh pemain atau belum.

Fungsi / Method Utama:

update(): Menggeser posisi koordinat X pipa ke arah kiri sesuai dengan tingkat kecepatan pergerakan yang ditentukan.

getBoundsTop() & getBoundsBottom(): Mengembalikan bentuk area pembatas fisik (Rectangle) untuk pipa bagian atas dan pipa bagian bawah.

isPassed() / setPassed(boolean status): Memeriksa dan menandai status pipa yang telah berhasil dilalui pemain untuk mencegah akumulasi skor ganda.

## Class Sound.java

Deskripsi: Pengelola sistem efek suara dan musik latar belakang aplikasi menggunakan pustaka audio Java (Clip / AudioInputStream).

Fungsi / Method Utama:

loadSound(String filePath): Memuat file audio dari direktori proyek ke memori.

play(): Memutar efek suara tunggal saat terjadi aksi tertentu (seperti lompat, mendapat skor, atau benturan).

loop(): Memutar trek audio secara terus-menerus sebagai musik latar belakang (background music).

stop(): Menghentikan eksekusi pemutaran suara.

# Dokumentasi
