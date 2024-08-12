MineSweeper Oyunu
Bu Java projesi, basit bir Mayın Tarlası oyununu simüle eder. Oyunun amacı, mayınlara dokunmadan tüm boş hücreleri açmaktır.

Nasıl Çalıştırılır?
Projeyi indirin veya klonlayın.
Kodu bir Java IDE'sinde (Eclipse, IntelliJ IDEA, vb.) açın.
MineSweeper.java dosyasını çalıştırın.
Oyun alanı boyutlarını girin (en az 2x2 olmalıdır).
Oyunu oynayın ve kazanmaya çalışın!
Oyun Kuralları
Oyun alanı boyutları en az 2x2 olmalıdır.
Başlangıçta, mayınlar rastgele yerleştirilir.
Oyuncu her hamlesinde bir hücre seçer.
Seçilen hücrede mayın varsa oyun biter.
Mayın olmayan hücrelerin etrafındaki mayın sayısı gösterilir.
Tüm boş hücreler açıldığında ve hiçbir mayına dokunulmadığında oyun kazanılır.
Kod İçeriği
MineSweeper sınıfı oyunun ana mantığını içerir.
startGame() metodu oyunu başlatır ve oyun akışını yönetir.
initializeBoard() metodu oyun tahtasını ve mayınların yerleşimini oluşturur.
printBoard() metodu oyun tahtasını ekrana basar.
playTurn() metodu kullanıcıdan hamle alır ve oyun durumunu kontrol eder.
Diğer metotlar ise oyunun farklı işlemlerini gerçekleştirir.
Notlar
Oyun alanı boyutları en az 2x2 olmalıdır.
Mayınlar rastgele yerleştirilir ve her oyun başında değişebilir.
Oyun tahtası her hamlede güncellenir ve kullanıcıya gösterilir.
