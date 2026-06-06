package com.example.data

// Metal ve Metal-Oksit Nanoparçacık Modeli
data class Nanoparticle(
    val symbol: String,
    val name: String,
    val category: String,
    val sizeRange: String,
    val mainApplication: String, // Eşleştirme oyununda kullanılacak hedef alan
    val description: String,
    val greenSynthesisMethod: String
)

// Yeşil Kimya İlkesi Modeli
data class GreenChemistryPrinciple(
    val number: Int,
    val title: String,
    val tagline: String,
    val description: String
)

// Nanoteknolojik Kavram Modeli
data class NanoConcept(
    val title: String,
    val subtitle: String,
    val definition: String,
    val realLifeExample: String
)

// Yeşil Kimya Doğru/Yanlış Soru Kartı Modeli
data class ChemistryScenario(
    val id: Int,
    val title: String,
    val paragraph: String,
    val isGreen: Boolean, // Yeşil kimyaya uygun mu? (Doğru/Yanlış)
    val explanation: String // Ayrıntılı Türkçe bilimsel açıklama
)

object NanoData {

    // 30 Popüler Metal ve Metal-Oksit Nanoparçacık
    val nanoparticles = listOf(
        Nanoparticle(
            symbol = "Ag",
            name = "Gümüş Nanoparçacıkları",
            category = "Asil Metal",
            sizeRange = "10 - 50 nm",
            mainApplication = "Antibakteriyel ambalajlar ve yara pansuman malzemeleri",
            description = "Yüzey alanının genişliği sayesinde bakterilerin hücre duvarını bozarak üremelerini engeller. Tıbbi araçlar, tekstil ve gıda saklama kaplarında yoğun olarak tercih edilir.",
            greenSynthesisMethod = "Gümüş nitrat (AgNO3) çözeltisi, doğal çay yaprağı özütü veya aloe vera jeli ile karıştırılarak oda sıcaklığında tamamen toksik kimyasallardan uzak şekilde yeşil yöntemle indirgenir."
        ),
        Nanoparticle(
            symbol = "Au",
            name = "Altın Nanoparçacıkları",
            category = "Asil Metal",
            sizeRange = "5 - 30 nm",
            mainApplication = "Tümör hücrelerini hedefleyen kanser teşhisi ve ilaç taşıma sistemleri",
            description = "Yüzey plazmon rezonansı göstermesi nedeniyle ışığı güçlü şekilde saçar ve emer. Kanserli hücrelerin tespiti ve fototermal olarak yakılarak yok edilmesinde çığır açmıştır.",
            greenSynthesisMethod = "Altın klorürün (HAuCl4) saf bal veya kırmızı üzüm çekirdeği özütü ile hafifçe ısıtılarak çevre dostu şekilde altın atomlarına indirgenmesi sağlanır."
        ),
        Nanoparticle(
            symbol = "ZnO",
            name = "Çinko Oksit Nanoparçacıkları",
            category = "Metal Oksit / Yarı İletken",
            sizeRange = "20 - 80 nm",
            mainApplication = "Güneş kremi UV koruyucu filtreleri ve kozmetik ürünler",
            description = "Görünür ışığı geçirirken ultraviyole (UV-A ve UV-B) ışınları mükemmel derecede bloke eder. Ciltte beyaz tabaka bırakmayan modern şeffaf güneş kremlerinin ana bileşenidir.",
            greenSynthesisMethod = "Çinko asetat tuzu, fesleğen özü veya papatya yağı katkısıyla bazik ortamda su içinde sentezlenerek tehlikeli kimyasal kalıntıların önüne geçilir."
        ),
        Nanoparticle(
            symbol = "TiO2",
            name = "Titanyum Dioksit Nanoparçacıkları",
            category = "Metal Oksit / Yarı İletken",
            sizeRange = "15 - 45 nm",
            mainApplication = "Kendi kendini temizleyen akıllı dış cephe boyaları ve camlar",
            description = "Güneş ışığı (UV) altında süper-hidrofilik ve fotokatalitik etki kazanır. Camın veya boyanın üzerine gelen organik kirleri parçalar ve yağmur suyuyla iz bırakmadan akmasını sağlar.",
            greenSynthesisMethod = "Titanyum tetraizopropoksidin, turunçgil kabuğu yağları kullanılarak oda sıcaklığında hidrolize tabi tutulmasıyla yeşil sentezi gerçekleştirilir."
        ),
        Nanoparticle(
            symbol = "Fe3O4",
            name = "Magnetit (Demir Oksit) Nanoparçacıkları",
            category = "Geçiş Metali Oksidi / Manyetik",
            sizeRange = "10 - 30 nm",
            mainApplication = "Kanser termoterapisi (hücrelerin ısıtılarak yok edilmesi) ve MR görüntüleme",
            description = "Süperparamanyetik özellik gösterirler. Harici bir manyetik alan uygulandığında vücut içinde yönlendirilebilir ve değişken manyetik alanla hedeflenen tümörü yakıp ısı üretebilirler.",
            greenSynthesisMethod = "Demir tuzları, meşe ağacı palamudu tanenleri veya enginar yaprağı sulu özleri eşliğinde, çevreye zararsız bir şekilde siyah renkli magnetit kristallerine dönüştürülür."
        ),
        Nanoparticle(
            symbol = "Fe",
            name = "Sıfır Değerlikli Demir Nanoparçacıkları",
            category = "Geçiş Metali / Reaktif",
            sizeRange = "30 - 90 nm",
            mainApplication = "Yeraltı sularındaki zehirli ağır metallerin giderilmesi ve arıtılması",
            description = "Yüksek kimyasal reaktivitesi ve indirgeme gücü ile bilinir. Toprak ve yeraltı sularına enjekte edildiğinde klorlu solventleri, kurşun ve arsenik gibi zehirli ağır metalleri kararsızlaştırıp çöktürür.",
            greenSynthesisMethod = "Demir sülfat çözeltisi, yüksek polifenol içeren yeşil çay demindeki doğal bileşenler yardımıyla reaksiyon kabında hızla çöktürülerek elde edilir."
        ),
        Nanoparticle(
            symbol = "Cu",
            name = "Bakır Nanoparçacıkları",
            category = "Geçiş Metali",
            sizeRange = "25 - 70 nm",
            mainApplication = "Tarımda mantar hastalıklarına karşı koruyucu zirai ilaç formülasyonları",
            description = "Çok güçlü antifungal ve antimikrobiyal etkiye sahiptirler. Tarım ürünlerinde yaygın görülen küf ve mantar hastalıklarına karşı düşük dozlarda bile koruma sağlar.",
            greenSynthesisMethod = "Bakır sülfat tuzu, çam iğnesi ekstresi veya limon suyu (sitrik asit kaynağı) ile yeşil kimya koşullarında kapalı ortamda sentezlenir."
        ),
        Nanoparticle(
            symbol = "Pt",
            name = "Platin Nanoparçacıkları",
            category = "Yarı Soy Metal / Katalizör",
            sizeRange = "2 - 15 nm",
            mainApplication = "Hidrojen yakıt hücrelerinde oksijen indirgenmesini sağlayan yüksek etkili katalizör",
            description = "Oksijen moleküllerini atomik seviyede parçalayarak protonlarla birleşmesini çok hızlandırır. Temiz hidrojen enerjisi üreten modern membran sistemlerinin kalbidir.",
            greenSynthesisMethod = "Platin tuzu, sarımsak özü veya ısırgan otu yaprağı ekstraktlarının indirgeyici gücüyle su bazlı çözeltilerde oda sıcaklığında çöktürülür."
        ),
        Nanoparticle(
            symbol = "Pd",
            name = "Palladyum Nanoparçacıkları",
            category = "Yarı Soy Metal / Katalizör",
            sizeRange = "5 - 20 nm",
            mainApplication = "Otomotiv egzoz gazı temizleme katalizörleri ve hidrojen algılayıcı sensörler",
            description = "Kendi hacminin yüzlerce katı hidrojen gazını emebilir ve organik sentezlerde (Heck reaksiyonu gibi) bağ oluşumlarını muazzam derecede katalizler. Egzozlardaki zehirli gazları zararsıza çevirir.",
            greenSynthesisMethod = "Karanfil taneleri özütü veya tarçın kabuğu sulu çözeltisi kullanılarak klorür tuzlarından kontrollü nano kristal büyümesiyle yeşil sentezi yapılır."
        ),
        Nanoparticle(
            symbol = "SiO2",
            name = "Silika (Silis) Nanoparçacıkları",
            category = "Ametal Oksit / Gözenekli",
            sizeRange = "10 - 100 nm",
            mainApplication = "Kanser ilaçlarının gözenekli yapısı içinde güvenle taşınıp salınımı",
            description = "Mezogözenekli yapıları sayesinde içlerinde yüksek miktarda aktif ilaç molekülü barındırabilirler. Vücut içinde hedefin tepesine ulaşana dek ilacı korurlar.",
            greenSynthesisMethod = "Çeltik kabuğu külü (pirinç üretimi atığı) veya şeker kamışı yaprak küllerindeki doğal silis kaynaklarının alkali ekstraksiyonla geri kazanılmasıyla elde edilir."
        ),
        Nanoparticle(
            symbol = "Al2O3",
            name = "Alümina Nanoparçacıkları",
            category = "Metal Oksit / Seramik",
            sizeRange = "30 - 80 nm",
            mainApplication = "Aşınmaya son derece dayanıklı seramik kesici aletler ve malzeme koruması",
            description = "Son derece yüksek sertlik, erime noktası ve aşınma direncine sahiplerdir. Uzay endüstrisinde, yüksek sıcaklık fırınlarında ve koruyucu zırhlı kaplamalarda hammadde olarak kullanılırlar.",
            greenSynthesisMethod = "Alüminyum nitrat, kahve telvesi özütü veya zeytin yaprağı atıklarının sulu çözeltisiyle karıştırılıp kurutularak yeşil yöntemle ısıtılır."
        ),
        Nanoparticle(
            symbol = "Ni",
            name = "Nikel Nanoparçacıkları",
            category = "Geçiş Metali / Manyetik",
            sizeRange = "20 - 60 nm",
            mainApplication = "Manyetik kayıt cihazları, bilgisayar sabit diskleri ve depolama birimleri",
            description = "Yüksek manyetik doygunluk ve manyetodirenç sunarlar. Dijital verilerin mikroskobik ölçekte bozulmadan depolanmasını ve yüksek hızlı yazılıp okunmasını mümkün kılarlar.",
            greenSynthesisMethod = "Nikel tuzlarının kekiç otu (kekik) veya biberiye özlerinden elde edilen antioksidan fenoller yardımıyla su bazlı ortamda indirgenmesiyle üretilir."
        ),
        Nanoparticle(
            symbol = "Co",
            name = "Kobalt Nanoparçacıkları",
            category = "Geçiş Metali / Manyetik",
            sizeRange = "15 - 50 nm",
            mainApplication = "Elektrikli otomobil bataryalarında şarj kapasitesini artıran elektrot alaşımları",
            description = "Elektrot malzemelerinin elektrokimyasal kararlılığını artırırlar ve pillerin ısıl kaçak yapmadan çok daha yüksek akımlarla hızlıca dolmasını sağlarlar.",
            greenSynthesisMethod = "Mısır nişastası veya jelatin gibi tamamen biyo-uyumlu polimerik matrislerin şablon (şablon destekli) yöntemiyle yeşil indirgenmesiyle hazırlanır."
        ),
        Nanoparticle(
            symbol = "CeO2",
            name = "Seryum Oksit Nanoparçacıkları",
            category = "Lantanit Oksit / Katalitik",
            sizeRange = "10 - 40 nm",
            mainApplication = "Dizel yakıt katkısı olarak yanmayı optimize etme ve kurum salınımını azaltma",
            description = "Ce3+ ve Ce4+ oksidasyon basamakları arasında hızlıca geçiş yapabildiği için oksijen depolama tankı gibi davranır. Yakıtın tam yanmasını sağlayarak partikül emisyonunu önler.",
            greenSynthesisMethod = "Seryum nitrat, zerdeçal (kurkumin) ekstresi veya zencefil kökü çözeltisi kullanılarak sulu ortamda çöktürülür."
        ),
        Nanoparticle(
            symbol = "ZrO2",
            name = "Zirkonya Nanoparçacıkları",
            category = "Metal Oksit / Seramik",
            sizeRange = "20 - 60 nm",
            mainApplication = "Diş ve kemik implantları, kemik çimentoları gibi dayanıklı biyomalzemeler",
            description = "Biyo-uyumluluğu ve kırılma tokluğu olağanüstü düzeydedir. Canlı vücudunun reddetmediği, diş kaplamaları ve yapay eklem protezlerinde kullanılan en sağlam seramik nanomalzemedir.",
            greenSynthesisMethod = "Zirkonyum klorürün, okaliptüs yaprağı ekstresi yardımıyla hidrolize edilip nötrleştirilmesiyle elde edilir."
        ),
        Nanoparticle(
            symbol = "ITO",
            name = "İndiyum Kalay Oksit Nanoparçacıkları",
            category = "Karışık Metal Oksit / İletken",
            sizeRange = "30 - 100 nm",
            mainApplication = "Akıllı telefon ekranlarındaki dokunmatik şeffaf elektrot devreleri",
            description = "Eşsiz bir şekilde hem görünür ışığa karşı tamamen şeffaf hem de elektriksel olarak iletkendir. Dokunmatik LCD/OLED ekranların, akıllı pencerelerin ve güneş pillerinin şeffaf elektrodudur.",
            greenSynthesisMethod = "İndiyum ve kalay tuzlarının, yeşil elma suyu özü yardımıyla birlikte çöktürülmesi ve kalsine edilmesiyle oluşturulur."
        ),
        Nanoparticle(
            symbol = "Bi2O3",
            name = "Bizmut Oksit Nanoparçacıkları",
            category = "Metal Oksit / Radyasyon",
            sizeRange = "15 - 50 nm",
            mainApplication = "Medikal radyoloji odalarında kurşunsuz hafif radyasyon zırhı panelleri",
            description = "Yüksek atom numarası sayesinde X ve Gama ışınlarını çok güçlü şekilde soğurur. Ağır ve toksik olan kurşun levhalara sağlıklı ve çevre dostu bir nano alternatif sunar.",
            greenSynthesisMethod = "Bizmut nitrat tuzu, nane yaprağı sulu özütü veya bal sirkesi kombinasyonuyla toksik solvent kullanılmadan yeşil sentezlenir."
        ),
        Nanoparticle(
            symbol = "WO3",
            name = "Tungsten Oksit Nanoparçacıkları",
            category = "Metal Oksit",
            sizeRange = "20 - 70 nm",
            mainApplication = "Elektrokromik akıllı camlar (voltajla kararan akıllı pencereler)",
            description = "Üzerinden çok hafif bir elektrik akımı geçirildiğinde rengini şeffaftan koyu maviye dönüştürebilir. Binalarda klima kullanımını azaltan akıllı pencerelerde kullanılır.",
            greenSynthesisMethod = "Sodyum tungstat sulu çözeltisi, limon kabuğu özü ve sitrik asit yardımıyla hidrotermal yeşil kimya koşullarında sentezlenir."
        ),
        Nanoparticle(
            symbol = "Mn3O4",
            name = "Manganez Oksit Nanoparçacıkları",
            category = "Metal Oksit",
            sizeRange = "10 - 45 nm",
            mainApplication = "Yüksek hızlı şarj olabilen süperkapasitör enerji depolama pilleri",
            description = "Olağanüstü yüksek ve hızlı yük transfer kabiliyeti sayesinde saniyeler içinde şarj olup gücü geri verebilen elektrokimyasal kapasitörlerin yapımında elektrotdur.",
            greenSynthesisMethod = "Manganez klorür çözeltisi, patates nişastası matrisi ve üzüm suyu yardımıyla oda sıcaklığında alkalileştirilerek elde edilir."
        ),
        Nanoparticle(
            symbol = "SnO2",
            name = "Kalay Oksit Nanoparçacıkları",
            category = "Metal Oksit",
            sizeRange = "15 - 55 nm",
            mainApplication = "Evlerde sızıntıları algılayan zehirli karbonmonoksit gaz dedektörleri",
            description = "Yüzeyinde gaz molekülleri adsorbe olduğunda elektriksel iletkenliği anında değişir. Havadaki zehirli yarı iletken gaz artışlarını milyonda bir (ppm) seviyesinde ölçebilir.",
            greenSynthesisMethod = "Kalay klorürün, çayır papatyası çiçeği özleri içeren sulu solüsyonla reaksiyonundan üretilir."
        ),
        Nanoparticle(
            symbol = "MgO",
            name = "Magnezyum Oksit Nanoparçacıkları",
            category = "Metal Oksit / Seramik",
            sizeRange = "25 - 90 nm",
            mainApplication = "Sanayi tipi fırınlarda kullanılan ateşe dayanıklı yüksek sıcaklık tuğlaları",
            description = "Isıyı mükemmel bir şekilde yalıtırken ergime sıcaklığı neredeyse 2800 santigrat derecedir. Metal döküm fırınlarının ve fırın iç kaplamalarının dayanımını artırır.",
            greenSynthesisMethod = "Magnezyum klorür, nar kabuğu ekstresindeki yüksek fenolik asitler yardımıyla fırınlamaya hazır magnezyum hidroksite dönüştürülür."
        ),
        Nanoparticle(
            symbol = "CuO",
            name = "Bakır Oksit Nanoparçacıkları",
            category = "Metal Oksit",
            sizeRange = "20 - 65 nm",
            mainApplication = "Ahşap direklerin çürümesini önleyen emprenye edici koruyucu vernikler",
            description = "Böceklerin, mantarların ve nemin ahşap hücrelerine sızarak yapıyı yemesini engelleyen kalıcı ve çevre dostu bir nano-bariyer oluşturur.",
            greenSynthesisMethod = "Sığır kuyruğu otu ekstraktı çözeltisi ve bakır sülfat tuzu kullanılarak su bazlı yeşil reaktörle sentezlenir."
        ),
        Nanoparticle(
            symbol = "Ag2S",
            name = "Gümüş Sülfür Kuantum Noktaları",
            category = "Yarı İletken / Kuantum Noktası",
            sizeRange = "2 - 10 nm",
            mainApplication = "Canlı dokuların kızılötesi ışıkla derinlemesine görüntülenmesini sağlayan kuantum noktaları",
            description = "Yakın kızılötesi bölgede (NIR) güçlü ışık yayarlar. Vücut dokuları bu dalga boyundaki ışığı engellemediği için kanserli bölgelerin derin mikroskobik incelemesini açar.",
            greenSynthesisMethod = "Gümüş nitrat ve sülfür kaynağı, maya hücreleri (Saccharomyces cerevisiae) kültüründe biyolojik olarak kendi kendine sentezletilir."
        ),
        Nanoparticle(
            symbol = "CdSe",
            name = "Kadmiyum Selenür Nanoparçacıkları",
            category = "Yarı İletken / Kuantum Noktası",
            sizeRange = "3 - 8 nm",
            mainApplication = "Ultra yüksek canlılıkta renk sunan QLED televizyon ekran teknolojileri",
            description = "Kuantum sınırlama etkisiyle, boyutu sadece 1-2 nanometre değiştiğinde yaydığı ışığın rengi maviden kırmızıya kayar. Pikselleri en net renk kalitesini sağlayan kristallerdir.",
            greenSynthesisMethod = "Yeşil kimya ilkeleri gereği toksik solventler (TOPO gibi) yerine zeytinyağı bazı ve sarımsaktan üretilen kükürt ajanlarıyla su banyosunda sentezi yapılabilmektedir."
        ),
        Nanoparticle(
            symbol = "Ru",
            name = "Rutenyum Nanoparçacıkları",
            category = "Geçiş Metali / Katalizör",
            sizeRange = "5 - 18 nm",
            mainApplication = "Işığa duyarlı güneş panellerinde elektron akışını tetikleyen duyarlılaştırıcı boyalar",
            description = "Görünür güneş spektrumunun neredeyse tamamını elektriğe dönüştürerek boya duyarlılaştırılmış yeni nesil solar pillerin (DSSC) verimini %15 artırabilir.",
            greenSynthesisMethod = "Rutenyum klorür sulu çözeltisinin, doğal kuşburnu çayı özütü ile ısıtılarak tek aşamada indirgenmesiyle elde edilir."
        ),
        Nanoparticle(
            symbol = "La2O3",
            name = "Lantan Oksit Nanoparçacıkları",
            category = "Nadir Toprak Oksidi",
            sizeRange = "20 - 60 nm",
            mainApplication = "Profesyonel kamera lenslerinde ışık kırılmasını iyileştiren yüksek performanslı optikler",
            description = "Cama karıştırıldığında camın ışık kırılma indisini artırırken ışık dağılımını (sapmasını) azaltır. Distorsiyonsuz, göz seviyesinde ultra net görüntüler veren lenslerin ham maddesidir.",
            greenSynthesisMethod = "Lantan hekzahidratının, hindistancevizi suyu veya ısırgan yaprağı özütü ile kompleksleştirilip kalsine edilmesiyle elde edilir."
        ),
        Nanoparticle(
            symbol = "Nd",
            name = "Neodimyum Nanoparçacıkları",
            category = "Lantanit / Manyetik",
            sizeRange = "15 - 40 nm",
            mainApplication = "Rüzgar türbinlerindeki jeneratörler için üretilen süper güçlü mıknatıslar",
            description = "Geleneksel mıknatıslardan katlarca daha güçlü manyetik alan üreten Neodimyum-Demir-Bor (NdFeB) kristal yapısının temelidir. Yeşil enerji rüzgar jeneratörlerinin vazgeçilmezidir.",
            greenSynthesisMethod = "Nadir toprak tuzlarının, yeşil narenciye özleri matrisinde biyolojik jel yöntemiyle yapılandırılarak sentezidir."
        ),
        Nanoparticle(
            symbol = "Cr2O3",
            name = "Krom Oksit Nanoparçacıkları",
            category = "Metal Oksit / Koruyucu",
            sizeRange = "25 - 60 nm",
            mainApplication = "Endüstriyel metal parçaların aşınmasını ve paslanmasını önleyen sert kaplama cilaları",
            description = "Sürtünme katsayısını çok düşürür ve korozyona maruz kalan çelik yüzeylerde oksijenin iç kısımlara sızmasını kalınlığı sadece mikron olan bir nano bariyerle önler.",
            greenSynthesisMethod = "Krom tuzlarının, yeşil çay yapraklarından elde edilen doğal polifenoller yardımıyla su fazında yeşil çöktürülmesidir."
        ),
        Nanoparticle(
            symbol = "MoS2",
            name = "Molibden Sülfür Nanotabakaları",
            category = "Kalkojenit / Yağlayıcı",
            sizeRange = "40 - 100 nm",
            mainApplication = "Uzay araçlarında kullanılan yüksek sıcaklığa dayanıklı kuru yağlama filmleri",
            description = "Grafen benzeri iki boyutlu bir yapıya sahiptir. Sürtünme anında tabakalar birbiri üzerinde tereyağı gibi kayar. Sıvı yağların buharlaşacağı ultra-vakum uzay koşullarında parça aşınmasını önler.",
            greenSynthesisMethod = "Amonyum heptamolibdat ve kükürt kaynaklarının biyolojik jelatin ve nişasta yardımıyla kontrollü fırınlanmasıyla yeşil sentezlenir."
        ),
        Nanoparticle(
            symbol = "V2O5",
            name = "Vanadyum Pentoksit Nanotelleri",
            category = "Metal Oksit / Enerji",
            sizeRange = "50 - 150 nm",
            mainApplication = "Yenilenebilir enerji depolama tesislerinde kullanılan yüksek kapasiteli akışkan piller",
            description = "Vanadyum iyonlarının 4 farklı yükseltgenme basamağını aynı anda yöneten redoks akış bataryalarının katotlarında kullanılır. Elektrik şebekesini rüzgar ve güneş dalgalanmalarına karşı dengeler.",
            greenSynthesisMethod = "Amonyum metavanadatın, muz kabuğu özütü yardımıyla yeşil hidrotermal sentez reaktörlerinde tel yapılarına dönüştürülmesidir."
        ),
        Nanoparticle(
            symbol = "In2O3",
            name = "İndiyum Oksit Nanoparçacıkları",
            category = "Metal Oksit / Yarı İletken",
            sizeRange = "10 - 45 nm",
            mainApplication = "Çevre havasını kirleten zehirli ozon gazını algılayan hassas sensörler",
            description = "Ozon gibi yüksek oksitleyici gazlarla reaksiyona girdiğinde elektriksel direncinde çok büyük değişimler sergiler. Çevre kirliliği ölçen istasyonların kalbidir.",
            greenSynthesisMethod = "İndiyum nitratın, yeşil nane yaprağı ve limon kabuğu sulu çözeltisiyle oda sıcaklığında hidrolizidir."
        ),
        Nanoparticle(
            symbol = "BaTiO3",
            name = "Baryum Titanat Nanoparçacıkları",
            category = "Karışık Metal Oksidi / Piezoelektrik",
            sizeRange = "30 - 90 nm",
            mainApplication = "Ayakkabı tabanına yerleştirilen ve yürüdükçe elektrik üreten piezoelektrik cihazlar",
            description = "Kendisine mekanik bir stres veya baskı uygulandığında kristal kafes yapısındaki asimetri nedeniyle elektrik gerilimi üreten mükemmel bir piezoelektrik malzemedir.",
            greenSynthesisMethod = "Baryum ve titanyum prekürsörlerinin, nişasta ve jelatin şablonunda su bazlı hidrotermal reaktörde sentezlenmesidir."
        ),
        Nanoparticle(
            symbol = "Bi2Se3",
            name = "Bizmut Selenür Nanotabakaları",
            category = "Metal Kalkojenit / Kuantum",
            sizeRange = "10 - 35 nm",
            mainApplication = "Süper hızlı kuantum bilgisayarlarda hata yapmayan topolojik devreler",
            description = "Malzemenin içi yalıtkan iken yüzeyi spin-kutup korumalı yüksek iletkenliktedir. Bu sayede saçılma yapmadan, elektronları kayıpsız iletebilecek kuantum cihazlarında kullanılır.",
            greenSynthesisMethod = "Bizmut ve selenyum tuzlarının, sarımsak özü glukozidleri ile sulu ortamda indirgenmesidir."
        ),
        Nanoparticle(
            symbol = "Gd2O3",
            name = "Gadoliniyum Oksit Nanoparçacıkları",
            category = "Nadir Toprak Oksidi / MR Kontrast",
            sizeRange = "5 - 25 nm",
            mainApplication = "Hastalık teşhislerinde güvenli manyetik rezonans (MR) kontrast görüntüleme sıvıları",
            description = "Güçlü paramanyetik özellikleri sayesinde su protonlarının spin-kafes gevşeme sürelerini kısaltır. MR cihazlarında yüksek çözünürlüklü keskin organ görüntüleri verir.",
            greenSynthesisMethod = "Gadoliniyum klorürün, nar kabuğu ekstresindeki doğal fenolik asitler eşliğinde yeşil çöktürülmesiyle elde edilir."
        ),
        Nanoparticle(
            symbol = "MoO3",
            name = "Molibden Trioksit Nanoplateletleri",
            category = "Metal Oksit / Elektrot",
            sizeRange = "20 - 75 nm",
            mainApplication = "Yüksek kapasiteli lityum iyon pillerin performansını artıran anot kaplamaları",
            description = "Yarı yarıya daha fazla lityum iyonu depolayabilen katmanlı bir kristal yapı sunar. Pillerin şarj döngüsünü ve ömrünü uzatan harika bir transition metal oksittir.",
            greenSynthesisMethod = "Amonyum molibdatın, kekik çayı sulu özlerindeki flavonoitler eşliğinde su fazında yeşil dönüşümüdür."
        ),
        Nanoparticle(
            symbol = "Co3O4",
            name = "Kobalt Oksit Nanoparçacıkları",
            category = "Geçiş Metali Oksidi / Katalizör",
            sizeRange = "15 - 50 nm",
            mainApplication = "Güneş enerjisiyle temiz su parçalayıp yeşil hidrojen gazı üreten hücreler",
            description = "Reaksiyonun enerji eşiğini düşürerek suyu oksijen ve hidrojene ayrıştırırken aşınmayan kararlı bir elektrokatalizördür.",
            greenSynthesisMethod = "Kobalt nitratın, yeşil serseri otu ve ısırgan bitkisi sulu özleriyle ısıl muamele altında sentezlenmesidir."
        ),
        Nanoparticle(
            symbol = "Y2O3",
            name = "Yitriyum Oksit Nanoparçacıkları",
            category = "Nadir Toprak Oksit / lüminesans",
            sizeRange = "25 - 80 nm",
            mainApplication = "Lazer cerrahi cihazlarında kullanılan kızılötesi lazer kristalleri",
            description = "Yüksek termal kararlılığa ve mükemmel ışık geçirgenliğine sahiptir. İçine katkılanan neodimyum ve tulyum gibi lantanitlerle birlikte ultra güçlü tıbbi lazer ışığı üretir.",
            greenSynthesisMethod = "Yitriyum nitratın, aloe vera jeli çözeltisinde oda sıcaklığında jel-kalsinasyon yöntemiyle yeşil elde edilmesidir."
        ),
        Nanoparticle(
            symbol = "ZnS",
            name = "Çinko Sülfür Kuantum Noktaları",
            category = "II-VI Grubu Yarı İletken",
            sizeRange = "5 - 30 nm",
            mainApplication = "Gece görüş kameralarında kızılötesi pencereler ve hassas mercekler",
            description = "Kızılötesi dalga boyundaki ışığı engellemeden yansıtan geniş bantlı bir yarı iletkendir. Termal kameralarda ve askeri optik pencerelerde kullanılır.",
            greenSynthesisMethod = "Çinko asetat ve sodyum sülfürün, tatlı portakal kabuğu yağı miselleri içinde tamamen organik çözücüsüz sentezidir."
        ),
        Nanoparticle(
            symbol = "SnO",
            name = "Kalay Monoksit İnce Filmleri",
            category = "Metal Oksit / İnce Film",
            sizeRange = "15 - 50 nm",
            mainApplication = "Katlanabilir ekranlardaki şeffaf ince film p-tipi transistörler",
            description = "Dizilime duyarlı p-tipi yarı iletken akışkanlığı sayesinde yüksek hızlı ve şeffaf katlanabilir ekran kartlarının, giyilebilir akıllı sensörlerin temelini oluşturur.",
            greenSynthesisMethod = "Kalay klorür sulu çözeltisinin, doğal nişasta ve mısır püskülü suyu şablon katalizörlüğünde yeşil sentezlenmesidir."
        ),
        Nanoparticle(
            symbol = "NiO",
            name = "Nikel Oksit Nanoyapıları",
            category = "Metal Oksit / Elektrokromik",
            sizeRange = "20 - 65 nm",
            mainApplication = "Göz kamaşmasını önleyen elektrikle kararan akıllı dikiz aynaları",
            description = "Voltaj uygulandığında nikel iyonlarının redoks tepkimesiyle ışık geçirgenliğini değiştirip kararır. Sürücülerin gözünü alan far yansımalarını önler.",
            greenSynthesisMethod = "Nikel sülfatün, biberiye ve yeşil çay antioksidan bitki sıvılarıyla su fazında sentezidir."
        ),
        Nanoparticle(
            symbol = "Fe2O3",
            name = "Hematit Demir Oksit Nanoparçacıkları",
            category = "Metal Oksit / Fotokatalizör",
            sizeRange = "10 - 45 nm",
            mainApplication = "Güneş ışığıyla sudaki zehirli organik tarım ilaçlarını parçalayan sistemler",
            description = "Görünür ışığı soğurabilen dar bant aralıklı bir yarı iletkendir. Güneş ışığını aldığında suda serbest radikaller üreterek kirliliği CO2 ve suya parçalar.",
            greenSynthesisMethod = "Demir klorürün, asma yaprağı ekstrelerindeki resveratrol fenolleri yardımıyla su ortamında çöktürülmesidir."
        ),
        Nanoparticle(
            symbol = "Sb2O3",
            name = "Antimon Trioksit Nanoparçacıkları",
            category = "Yarı Metal Oksit / Alev Geciktirici",
            sizeRange = "30 - 85 nm",
            mainApplication = "Uçak koltuklarında alev almayı geciktiren nano katkılı yanmaz kumaşlar",
            description = "Polimerlerle karıştırıldığında yangın anında açığa çıkan halojen radikallerini sönümlendirir ve oksijen örtüsü kurarak kumaşın alev almasını ve tutuşmasını geciktirir.",
            greenSynthesisMethod = "Antimon klorürün, elma sirkesindeki asetik asit katalizi ve nane suyu indirgemesiyle yeşil sentezidir."
        ),
        Nanoparticle(
            symbol = "Se",
            name = "Selenyum Nanoparçacıkları",
            category = "Yarı Metal / Besin Takviyesi",
            sizeRange = "20 - 70 nm",
            mainApplication = "Bağışıklığı güçlendiren ve kanserli hücreyi engelleyen antioksidan takviyeler",
            description = "Doğal selenit formuna kıyasla vücutta katlarca daha yüksek emilim ve daha düşük toksisite gösterir. Hücrelerdeki serbest radikalleri yok ederek yaşlanmayı önler.",
            greenSynthesisMethod = "Sodyum selenitin, doğal çam balı ve C vitamini (limon suyu) karışımıyla oda sıcaklığında indirgenerek gıda kalitesinde üretilmesidir."
        ),
        Nanoparticle(
            symbol = "Te",
            name = "Tellür Nanotelleri",
            category = "Yarı Metal / Termoelektrik",
            sizeRange = "40 - 120 nm",
            mainApplication = "Sanayi bacalarındaki atık ısıyı doğrudan temiz elektriğe dönüştüren jeneratörler",
            description = "Sıcaklık farkını doğrudan voltaj farkına (Seebeck etkisi) çeviren yüksek geçirgenliğe sahip nanotellerdir. Atık ısıyı kurtarma potansiyeli yüksektir.",
            greenSynthesisMethod = "Tellür asit tuzunun, papatya suyu ve glukoz (şeker) eşliğinde su bazlı yeşil reaktörde ipliksi kristal olarak büyütülmesidir."
        ),
        Nanoparticle(
            symbol = "Bi",
            name = "Bizmut Nanoparçacıkları",
            category = "Geçiş Sonrası Metal",
            sizeRange = "25 - 60 nm",
            mainApplication = "Zehirli kurşun yerine elektronik sanayisinde kullanılan çevre dostu lehimler",
            description = "Düşük erime noktası ve yüksek mekanik dayanıklılığı sayesinde toksik kurşunun (Pb) yarattığı muazzam elektronik atık kirliliğine karşı en temiz ve sürdürülebilir yeşil alternatiftir.",
            greenSynthesisMethod = "Bizmut nitratın, yeşil soğan kabuğu ve kırmızı soğan ekstresi polifenolleri ile su fazında yeşil indirgenmesidir."
        ),
        Nanoparticle(
            symbol = "Cu2O",
            name = "Bakır Oksit (Bakır I) Nanoparçacıkları",
            category = "Metal Oksit / Tarım",
            sizeRange = "15 - 45 nm",
            mainApplication = "Sebzelerde bakteriyel solgunluk hastalıklarına karşı koruyucu bitki spreyleri",
            description = "Bakır iyonlarının kontrollü salımıyla bakteri ve mantarların solunum zincirini kırar. Kimyasal zirai ilaç tüketimini yarıya indiren doğal bitki savunucusudur.",
            greenSynthesisMethod = "Bakır sülfat tuzunun, pancar pekmezi ve glukoz çözeltisiyle bazik ortamda su içinde yeşil dönüşümüdür."
        ),
        Nanoparticle(
            symbol = "Eu2O3",
            name = "Europium Oksit Nanoparçacıkları",
            category = "Nadir Toprak Oksit / Lüminesans",
            sizeRange = "10 - 35 nm",
            mainApplication = "Banknotlar üzerine basılan görünmez mor ötesi (UV) güvenlik mürekkepleri",
            description = "Görünür ışıkta şeffaf iken mor ötesi (UV) ışık altında göz alıcı kararlı bir kırmızı floresan ışıma yayar. Pasaport ve banknotların taklit edilmesini önler.",
            greenSynthesisMethod = "Europium klorürün, zencefil kökü ekstresi ve jelatin şablonunda su bazlı hidrotermal olarak sentezlenmesidir."
        ),
        Nanoparticle(
            symbol = "TiC",
            name = "Titanyum Karbür Nanoparçacıkları",
            category = "Metal Karbür / Süper Sert",
            sizeRange = "30 - 100 nm",
            mainApplication = "Yüksek hızlı matkap uçlarında aşınmayı önleyen süper sert koruyucu filmler",
            description = "Elmasa yakın sertliği ve 3100 derecenin üzerindeki erime noktası ile metallerin kesilmesi ve işlenmesi sırasında matkap uçlarının aşırı ısınmasını ve körelmesini önler.",
            greenSynthesisMethod = "Titanyum oksit ve doğal karbon kaynağı şeker pancarı külünün sulu ortamda şablonlanarak kalsinasyonudur."
        ),
        Nanoparticle(
            symbol = "Ta2O5",
            name = "Tantal Pentoksit Nanoyapıları",
            category = "Metal Oksit / Dielektrik",
            sizeRange = "15 - 55 nm",
            mainApplication = "Bilgisayar işlemcilerinde elektrik kaçaklarını önleyen mikroçip kapasitörleri",
            description = "Çok yüksek dielektrik sabitine (high-k) sahiptir. Bu sayede atomik kalınlıktaki silikon katmanlarda bile yük depolayarak bilgisayar pillerinin bekleme tüketimini azaltır.",
            greenSynthesisMethod = "Tantal klorürün, yeşil elma asitleri (malik asit vb.) ve okaliptüs yaprağı yağı hydration'uyla sulu sentezidir."
        ),
        Nanoparticle(
            symbol = "LiCoO2",
            name = "Lityum Kobalt Oksit Nanoparçacıkları",
            category = "Karışık Metal Oksidi / Lityum Pil",
            sizeRange = "40 - 150 nm",
            mainApplication = "Akıllı cep telefonlarımızın gün boyu açık kalmasını sağlayan lityum piller",
            description = "Lityum iyonlarını verimli bir şekilde interkalasyonla yapısında barındırabilen, yüksek enerji yoğunluklu katot elektrodudur. Bugün taşınabilir devrimin temel taşıdır.",
            greenSynthesisMethod = "Lityum ve kobalt asetat tuzlarının, pektin (meyve lifi jeli) yardımıyla sulu sol-jel yöntemiyle yeşil sentezlenmesidir."
        )
    )

    fun getSectorAndEmoji(symbol: String): Pair<String, String> {
        return when (symbol) {
            "Ag", "Au", "Fe3O4", "SiO2", "ZrO2", "Bi2O3", "Ag2S", "Se", "Gd2O3" -> Pair("Tıp ve Sağlık", "🏥")
            "ZnO", "TiO2" -> Pair("Kozmetik ve UV Filtre", "🧴")
            "Fe", "Cu2O", "Fe2O3" -> Pair("Çevre ve Su Arıtma", "💧")
            "Cu", "CuO" -> Pair("Tarım ve Ziraat", "🌾")
            "Pt", "Co", "CeO2", "Mn3O4", "Ru", "V2O5", "BaTiO3", "MoO3", "Co3O4", "Te", "LiCoO2" -> Pair("Enerji ve Bataryalar", "🔋")
            "Al2O3", "MgO", "Cr2O3", "Sb2O3", "TiC" -> Pair("Mühendislik ve Ağır Sanayi", "🏭")
            "Ni", "ITO", "Nd", "Ta2O5", "Bi2Se3", "Bi" -> Pair("Elektronik ve Devreler", "🔌")
            "WO3", "CdSe", "La2O3", "Eu2O3", "ZnS", "SnO", "NiO" -> Pair("Ekranlar ve Optikler", "📺")
            "Pd", "SnO2", "In2O3" -> Pair("Gaz Algılama ve Sensör", "🔍")
            "MoS2" -> Pair("Havacılık ve Uzay", "🚀")
            else -> Pair("Multidisipliner", "🔬")
        }
    }

    // Yeşil Kimyanın 12 Temel Prensibi
    val greenPrinciples = listOf(
        GreenChemistryPrinciple(
            number = 1,
            title = "Atıkların Önlenmesi",
            tagline = "Arıtmak yerine baştan üretme!",
            description = "Atık oluştuktan sonra onu temizlemek veya filtrelemek yerine; kimyasal süreçleri en başından hiç atık üretmeyecek şekilde tasarlamak temel önceliktir."
        ),
        GreenChemistryPrinciple(
            number = 2,
            title = "Atom Ekonomisi",
            tagline = "Tüm atomlar nihai ürüne!",
            description = "Sentetik üretim yöntemleri, üretim sürecinde kullanılan tüm başlangıç maddelerini ve reaktif atomlarını nihai ürüne dönüştürecek şekilde, en yüksek verimle tasarlanmalıdır."
        ),
        GreenChemistryPrinciple(
            number = 3,
            title = "Daha Az Zararlı Sentez",
            tagline = "İnsan ve doğaya zarar vermeyen yöntemler!",
            description = "Sentez yöntemleri tasarlanırken, insan sağlığına ve çevreye çok az toksik olan hatta mümkünse hiç toksik olmayan ham maddeler kullanılmalı ve üretilmelidir."
        ),
        GreenChemistryPrinciple(
            number = 4,
            title = "Güvenli Kimyasal Tasarımı",
            tagline = "Etkili ama zehirsiz formüller!",
            description = "Yeni geliştirilen kimyasal ürünler ve moleküller, hedeflenen işlevselliklerini ve etkinliklerini tam korurken, insan ve çevre üzerindeki zehirlilik (toksisite) etkileri en aza indirilecek şekilde tasarlanmalıdır."
        ),
        GreenChemistryPrinciple(
            number = 5,
            title = "Güvenli Çözücüler",
            tagline = "Toksik tinerler yerine su!",
            description = "Reaksiyonlarda kullanılan çözücüler (solvent), ekstraksiyon ajanları veya diğer yardımcı maddeler mümkün olduğunca kullanılmamalıdır. Kullanılması zorunlu olduğunda ise su gibi tamamen zararsız çözücüler seçilmelidir."
        ),
        GreenChemistryPrinciple(
            number = 6,
            title = "Enerji Verimliliği",
            tagline = "Reaksiyonlar oda sıcaklığında!",
            description = "Kimyasal süreçlerin enerji gereksinimleri çevre ve ekonomi üzerindeki büyük etkileri nedeniyle asgariye indirilmelidir. Sentezler, yüksek sıcaklık veya basınç yerine, mümkünse oda sıcaklığında ve normal atmosferik basınçta gerçekleştirilmelidir."
        ),
        GreenChemistryPrinciple(
            number = 7,
            title = "Yenilenebilir Hammadde",
            tagline = "Petrol biter, tarımsal kaynaklar tükenmez!",
            description = "Teknik ve ekonomik açıdan uygun olduğu sürece, tükenmekte olan fosil kaynaklı kimyasallar yerine tarımsal atıklar, bitkisel yağlar gibi yenilenebilir doğal ham maddeler tercih edilmelidir."
        ),
        GreenChemistryPrinciple(
            number = 8,
            title = "Türevlerin Azaltılması",
            tagline = "Ekstra aşamalardan kaçın!",
            description = "Kimyasal sentezlerde gereksiz geçici koruma grupları, bloklayıcı ajanlar ve modifikasyon gibi ek aşamalardan kaçınılmalıdır. Her ek aşama daha fazla reaktif kullanımı ve daha fazla atık demektir."
        ),
        GreenChemistryPrinciple(
            number = 9,
            title = "Katalizör Kullanımı",
            tagline = "Stokiyometrik yerine katalitik oranlar!",
            description = "Reaksiyonlarda aşırı reaktif sarfiyatına yol açan stokiyometrik maddeler yerine, reaksiyonu hızlandıran ve kendisi harcanmayan yüksek seçici katalizörler tercih edilmelidir."
        ),
        GreenChemistryPrinciple(
            number = 10,
            title = "Parçalanabilir Tasarım",
            tagline = "İşlevi bitince doğada kaybolsun!",
            description = "Kimyasal ve plastik ürünler, kullanım süreleri bittikten sonra çevrede kalarak kirlilik yaratmayacak şekilde, doğada kolayca çözünüp tamamen zararsız mikro parçacıklara veya suya dönüşecek şekilde tasarlanmalıdır."
        ),
        GreenChemistryPrinciple(
            number = 11,
            title = "Gerçek Zamanlı Analiz",
            tagline = "Kirliliği oluşmadan yakala!",
            description = "Tehlikeli maddelerin oluşumunu ve reaksiyon kaçaklarını henüz gerçekleşmeden önce tespit etmek için, üretim sürecinin anlık, yerinde analiz edilmesini ve kontrol edilmesini sağlayan akıllı sensörler geliştirilmelidir."
        ),
        GreenChemistryPrinciple(
            number = 12,
            title = "Güvenli Kimya (Kaza Önleme)",
            tagline = "Patlamasız, yayılımsız, güvenli tesis!",
            description = "Bir kimyasal süreçte kullanılan maddeler ve bunların fiziksel halleri (gaz yerine sıvı vb.), gaz kaçakları, patlamalar, yangınlar ve fabrikada yaşanabilecek kazaları en baştan önleyecek şekilde dikkatle seçilmelidir."
        )
    )

    // Nanoteknoloji Temel Teorik Bilgileri
    val nanoConcepts = listOf(
        NanoConcept(
            title = "Nanoteknoloji Nedir?",
            subtitle = "Atomların ve Moleküllerin Mühendisliği",
            definition = "Maddenin atomik, moleküler düzeyde kontrol edilerek 1 ila 100 nanometre (metrenin milyarda biri) boyut aralığında yeni fonksiyonel yapılar ve malzemeler üretme bilimidir. Bu boyuttaki malzemeler, klasik makro boyutlu hallerinden tamamen farklı fiziksel, kimyasal ve optik özellikler gösterirler.",
            realLifeExample = "Klasik dökme altın sarı renkli ve kimyasal olarak pasiftir. Ancak altın parçacıkları 10 nm boyuta indirildiğinde rengi kırmızı/şarap rengine döner ve olağanüstü bir kimyasal katalizör haline gelir."
        ),
        NanoConcept(
            title = "Yüzey Alanı / Hacim Oranı",
            subtitle = "Neden Nano Boyut Bu Kadar Aktif?",
            definition = "Bir malzemenin boyutu küçüldükçe, toplam hacmine kıyasla dışarıda kalan yüzeyindeki atom sayısı üssü katlanarak artar. Bu durum reaksiyona giren aktif yer sayısını devasa oranda artırdığından, nano malzemeler inanılmaz derecede yüksek reaktivite ve hızlandırılmış katalitik güce kavuşurlar.",
            realLifeExample = "Büyük bir odun bloğu yavaş yanarken, aynı hacimdeki kerestenin talaş hali havayla daha fazla yüzeye temas ettiği için neredeyse patlayarak yanar. Nano boyutta bu reaksiyon yüzeyi milyonlarca kat daha etkilidir."
        ),
        NanoConcept(
            title = "Yeşil Sentez Kimyası",
            subtitle = "Çevre Dostu Nanoparçacık Üretimi",
            definition = "Geleneksel nanoteknoloji; nanoparçacıkları sentezlemek için sodyum borohidrür gibi toksik indirgenler ve çevreye zararlı organik çözücüler kullanır. Yeşil Sentez ise, bitki özlerindeki doğal antioksidanları (polifenoller, flavonoidler vb.) ve suyu kullanarak zararlı atık üretmeden güvenle üretim yapar.",
            realLifeExample = "Bitkilerdeki polifenollerin gümüş iyonlarını yakalayıp zararsız gümüş metal nanoparçacıklarına dönüştürmesi. Sentez sonrasında geriye sadece organik bitki artığı ve su kalır."
        ),
        NanoConcept(
            title = "Kuantum Sınırlandırması",
            subtitle = "Boyuta Bağlı Renk Değişimi",
            definition = "Yarı iletken malzemeler kritik bir nano boyuta (genellikle < 10 nm) düştüğünde, elektronların hareket alanı kısıtlanır ve enerji seviyeleri kuantum kurallarına göre ayrıklaşır. Bu durum, malzemenin sadece boyutunu değiştirerek yaydığı ışığın dalga boyunu (rengini) mavi, yeşil, sarı veya kırmızı olarak kontrol edebilmemizi sağlar.",
            realLifeExample = "QLED akıllı televizyon ekranlarında kullanılan Kadmiyum Selenür (CdSe) kuantum noktaları. Kristal boyutu 2 nm ise mavi, 5 nm ise yeşil, 8 nm ise parlak kırmızı ışık yayar."
        ),
        NanoConcept(
            title = "Nanotoksikoloji",
            subtitle = "Nano Dünyanın Güvenlik Sınırları",
            definition = "Nanoparçacıkların boyutlarının çok küçük olması, onların makro maddelerin geçemediği hücresel zarları ve kan-beyin bariyerini kolayca aşmasına izin verir. Nanotoksikoloji, bu benzersiz malzemenin çevre ve insan sağlığı üzerindeki biyolojik etkilerini inceleyerek yeşil kimya sınırlarında güvenli üretim formüllerini araştırır.",
            realLifeExample = "Gümüş nanoparçacıkların bakterilere karşı ölümcül olup insan hücresine zarar vermeyecek güvenli dozlarının ve biyolojik bozunma sürelerinin belirlenmesi."
        )
    )

    // Yeşil Kimya Doğru / Yanlış Oyun Soru Kampanyası (15 Özgün Kimyasal Senaryo)
    val chemistryScenarios = listOf(
        ChemistryScenario(
            id = 1,
            title = "Doğal Kaynaklı Sağlıklı Tekstil Boyası",
            paragraph = "Bir tekstil fabrikası, pamuklu tişörtleri renklendirmek amacıyla kömür katranından sentezlenen yapay anilin boyaları kullanmayı bırakmıştır. Bunun yerine, bölgede yetişen nar kabukları, zerdeçal özleri ve su bazlı çözeltiler kullanarak doğal boyama yapmaktadır. Bu süreç reaksiyon sonunda çevreye zararlı hiçbir kimyasal atık salmamaktadır.",
            isGreen = true,
            explanation = "Bu süreç Yeşil Kimyanın 'Tükenebilir fosil ham maddeler yerine tarımsal kaynaklı yenilenebilir ham maddeler kullanımı', 'Güvenli ve su bazlı çözücüler seçimi' ve 'Atıkların kaynağında tamamen önlenmesi' ilkelerine mükemmel derecede uymaktadır."
        ),
        ChemistryScenario(
            id = 2,
            title = "Çok Aşamalı Ağrı Kesici Sentezi",
            paragraph = "Bir ilaç laboratuvarı, yeni bir ağrı kesici formülü sentezlemek için 12 aşamalı uzun bir reaksiyon zinciri tasarlamıştır. Her aşamada yan ürünleri temizlemek ve safsızlıkları ayırmak amacıyla tonlarca kloroform (klorlu çözücü) kullanılmakta ve her aşamada atomların %80'i nihai ilaca girmeyip yan toksik atık olarak ayrılmaktadır.",
            isGreen = false,
            explanation = "Yalnızca %20 atom ekonomisi sunan bu süreç, aşırı yüksek seviyede toksik kloroform solvent tüketimi ve fazla yan aşama (koruyucu grup türevi) içermesi nedeniyle Yeşil Kimyanın 'Atık Önleme', 'Atom Ekonomisi', 'Güvenli Çözücüler' ve 'Türevlerin Azaltılması' ilkelerini feci şekilde ihlal eder."
        ),
        ChemistryScenario(
            id = 3,
            title = "Tarım Atığından Biyoplastik Sentezi",
            paragraph = "Biyokimya mühendisleri, tarlalarda arta kalan atık mısır koçanlarını ve şeker pancarı posalarını toplayarak ambalaj malzemesi üretmişlerdir. Bu üretim sürecinde çevre dostu enzimler (biyolojik katalizörler) kullanılmış ve reaksiyon dışarıdan hiçbir ısıtma yapılmadan tamamen oda sıcaklığında tamamlanmıştır.",
            isGreen = true,
            explanation = "Bu işlem Yeşil Kimya ilkelerinden 'Yenilenebilir ham madde kullanımı (tarım atıkları)', 'Biyolojik katalizör kullanımı' ve reaksiyonun oda sıcaklığında kurularak enerji israfının önlenmesini savunan 'Enerji Verimliliği' ilkelerine tam uyum gösterir."
        ),
        ChemistryScenario(
            id = 4,
            title = "Aşırı Dayanıklı Mikroplastik Şişeler",
            paragraph = "Bir ambalaj şirketi, gazlı içeceklerin sızdırmazlığını artırmak amacıyla yeni bir polimer plastik üretmiştir. Bu plastik o kadar sağlamdır ki, doğaya atıldığında en az 150 yıl boyunca fiziksel olarak hiç bozulmadan kalmakta, ancak bu sürenin sonunda toprağa zehirli plastik mikro partikülleri bırakarak ufalanmaktadır.",
            isGreen = false,
            explanation = "Bu süreç Yeşil Kimyanın 'Parçalanabilir Tasarım' ilkesini tamamen ihlal eder. Yeşil kimya ürünleri, ömürlerini tamamladıktan sonra doğada kalıcı olmamalı, mikroplastik kirliliği yaratmadan biyolojik olarak zararsız bileşenlere (su ve karbondioksite) ayrışmalıdır."
        ),
        ChemistryScenario(
            id = 5,
            title = "Hindistan Cevizli Çevre Dostu Deterjan",
            paragraph = "Bir deterjan fabrikası, göllerdeki ve nehirlerdeki alglerin aşırı büyümesine (ötrofikasyona) yol açarak sudaki oksijeni bitiren ve balıkları öldüren fosfat katkılarını kaldırmıştır. Bunun yerine deterjanlara hindistan cevizi yağından elde edilen ve nehir canlılarına hiç zarar vermeyen biyobozunur temizleyiciler eklemiştir.",
            isGreen = true,
            explanation = "Bu yenilikçi formül, Yeşil Kimyanın 'Etkinliği korurken toksisiteyi en aza indiren Güvenli Kimyasalların Tasarımı' ve 'Doğada kalıcı atık bırakmayan Parçalanabilir Tasarım' ilkeleri doğrultusunda geliştirilmiş harika bir yeşil kimya örneğidir."
        ),
        ChemistryScenario(
            id = 6,
            title = "Süper Nano-Katalizör İle Amonyak Üretimi",
            paragraph = "Büyük bir gübre fabrikası, tarımsal amonyak üretimi için reaktörlerine demir bazlı yeni bir nano-katalizör yerleştirmiştir. Bu sayede reaksiyonun gerçekleşmesi için gereken sıcaklık 550°C'den 120°C'ye düşürülmüş ve harcanan elektrik miktarında %70 oranında devasa bir elektrik tasarrufu elde edilmiştir.",
            isGreen = true,
            explanation = "Katalizör kullanımı (kataliz ilkesi) reaksiyon sıcaklıklarını düşürerek büyük oranda yakıt ve elektrik tasarruf edilmesini sağlar. Süreç, Yeşil Kimyanın hem 'Kataliz' hem de 'Enerji Verimliliği' ilkelerine son derece uygundur."
        ),
        ChemistryScenario(
            id = 7,
            title = "Cıva Katkılı Parlak Şampuan Üretimi",
            paragraph = "Bir şampuan markası, saçların yıkandıktan sonra göz kamaştırıcı derecede parlak görünmesini sağlamak için formülüne cıva tuzu içeren özel pırıltı kimyasalları eklemiştir. Fabrika, üretim sırasında oluşan cıva içerikli çamur atıkları ise nehir suyunda kolayca dağılacağını düşünerek doğrudan kanalizasyona vermiştir.",
            isGreen = false,
            explanation = "Canlılar için aşırı derecede zehirli (toksik) ve ağır metal olan cıvanın formülasyona eklenmesi ve atıkların doğaya doğrudan salınması Yeşil Kimyanın 'Güvenli Kimyasal Tasarımı' ve 'Atıkların Önlenmesi' ilkelerini kökten baltalayan, ağır çevre kirliliğine yol açan bir hatadır."
        ),
        ChemistryScenario(
            id = 8,
            title = "Fabrika Bacaları İçin Akıllı Sensör Sistemi",
            paragraph = "Kimya mühendisleri, sentetik duman salınımı yapan bacalara anlık ölçüm yapan kızılötesi lazer spektroskopisi duyargaları yerleştirmişlerdir. Bu akıllı sensörler, bacadan çıkan zehirli gaz oranında milisaniyelik bir artış sezdiği anda süreci otomatik olarak yavaşlatıp kimyasal sızıntıyı henüz duman çıkmadan durdurmaktadır.",
            isGreen = true,
            explanation = "Bu teknoloji Yeşil Kimyanın 11. ilkesi olan 'Kirliliğin Önlenmesi İçin Gerçek Zamanlı Analiz' ilkesine uymaktadır. Süreç üretim esnasında dinamik olarak taranıp, tehlike oluşmadan hemen önce müdahale edilmektedir."
        ),
        ChemistryScenario(
            id = 9,
            title = "Uçucu Solventler Yerine Su Bazlı Boya",
            paragraph = "Bir mobilya cilalama atölyesi, tiner (uçucu organik karbon bileşiği) bazlı vernikler yerine su bazlı akrilik ahşap koruyucular kullanmaya başlamıştır. Su bazlı cila sayesinde havaya yayılan yanıcı gaz miktarının sıfıra inmesiyle işçilerin solunum sistemi rahatlamış ve iş yerindeki yangın çıkma tehlikesi tamamen ortadan kalkmıştır.",
            isGreen = true,
            explanation = "Alev alabilen ve solunması kanserojen etki yaratan toksik organik çözücüler yerine zararsız suyun kullanılması Yeşil Kimyanın 'Güvenli Çözücüler' ve fabrikalarda yangın/patlama riskini asgari düzeye indiren 'Kaza Önleme İçin Güvenli Kimya' ilkelerine doğrudan uygundur."
        ),
        ChemistryScenario(
            id = 10,
            title = "Formaldehit Gazı Salan Hızlı Yapıştırıcı",
            paragraph = "Bir inşaat şirketi, oda sıcaklığında sadece 5 saniyede donan çok güçlü bir duvar paneli yapıştırıcısı tasarlamıştır. Fakat bu yapıştırıcının donma reaksiyonu sırasında kapalı ortama yüksek konsantrasyonda zehirli olan ve kanserojen etkilere yol açan formaldehit gazı yayılmakta ve odanın saatlerce havalandırılması gerekmektedir.",
            isGreen = false,
            explanation = "Reaksiyonun oda sıcaklığında gerçekleşmesi olumlu olsa da, insan sağlığına son derece zararlı, toksik ve kanserojen gaz salınımı yapması nedeniyle bu yöntem Yeşil Kimyanın 'Daha Az Zararlı Kimyasal Sentezi' ve 'Güvenli Kimya (Kaza Önleme)' ilkelerine aykırıdır."
        ),
        ChemistryScenario(
            id = 11,
            title = "Klor Gazı Yerine Enzimle Kağıt Beyazlatma",
            paragraph = "Büyük bir kağıt fabrikası, saman ve odun hamurunu beyazlatmak için önceden kullandığı nehir sularına karışıp dioksin gibi ölümcül zehirlere dönen klor gazını tamamen yasaklamıştır. Beyazlatma reaksiyonu için artık maya hücrelerinden elde edilen doğal enzimler ve reaksiyonu suya dönüşen hidrojen peroksit kullanılmaktadır.",
            isGreen = true,
            explanation = "Zehirli klor yerine sadece yan ürün olarak temiz suya ve oksijene ayrışan hidrojen peroksit ve biyolojik enzim katalizörlerinin seçilmesi Yeşil Kimyanın 'Daha Az Zararlı Sentezler', 'Güvenli Kimyasal Tasarımı' ve 'Kataliz' kurallarına son derece uygundur."
        ),
        ChemistryScenario(
            id = 12,
            title = "Seçici Olmayan Tarım Zehirleri",
            paragraph = "Bir zirai ilaç şirketi, pamuk tarlalarındaki zararlı yaprak bitlerini öldürmek için çok güçlü kimyasal bir zehir geliştirmiştir. Bu zehir pamuk bitlerini kurtararak verimi %15 artırırken, tarlaya gelen yararlı bal arılarının, kelebeklerin ve toprak solucanlarının tümünün sinir sistemini çökerterek telef etmektedir.",
            isGreen = false,
            explanation = "Hedef dışı yararlı ekosistem canlılarına (arılar ve solucanlar gibi tarım dostlarına) zarar veren kimyasalların üretimi Yeşil Kimyanın 'Güvenli Kimyasalların Tasarımı' ve doğaya toksik yük bindirmeme esaslarına tamamen aykırıdır."
        ),
        ChemistryScenario(
            id = 13,
            title = "Yeşil Çay Ekstresi İle Nano-Gümüş Sentezi",
            paragraph = "Bir okul laboratuvarında, yara bandı üretmek için gümüş kaplama nanoparçacıklar sentezlenecektir. Öğrenciler tehlikeli sodyum borohidrür kimyasalı yerine, demlenmiş yeşil çay çözeltisi ve gümüş nitrat tuzunu karıştırmışlardır. 10 dakika içinde hiçbir atık gaz çıkmadan çözelti kahverengiye dönmüş ve gümüş nanoparçacıkları üretilmiştir.",
            isGreen = true,
            explanation = "Bu süreç 'Yeşil Nanoteknolojinin' mükemmel bir örneğidir. Toksik laboratuvar indirgenleri yerine, yeşil çaydaki zararsız antioksidan polifenollerin kullanılması Yeşil Kimyanın 'Daha Az Zararlı Sentez' ve 'Yenilenebilir Hammadde' prensipleri ile birebir örtüşür."
        ),
        ChemistryScenario(
            id = 14,
            title = "Gece Saklanan Asit Banyosu Atıkları",
            paragraph = "Bir metal işleme fabrikası, çelik yüzeyleri pürüzsüzleştirmek için kullandığı sülfürik asit banyolarını düzenli olarak arıtma tesisine gönderip nötralize etmenin çok pahalı olduğunu fark etmiştir. Şirket, maliyetten kısmak amacıyla asit atıklarını varillere doldurup gece yarısı ıssız arazilere gizlice dökmektedir.",
            isGreen = false,
            explanation = "Zehirli atıkların arıtılmadan doğaya vahşice deşarj edilmesi Yeşil Kimyanın 'Atıkların Önlenmesi', 'Daha Az Zararlı Kimyasal Sentez' kurallarını ve en basit çevre etiği yasalarını çiğneyen, ağır toprak/su kirliliğine sebep olan kanun dışı bir eylemdir."
        ),
        ChemistryScenario(
            id = 15,
            title = "Şeker Pancarından Gübreleşebilen Bardak",
            paragraph = "Bir cafe zinciri, petrol türevli tek kullanımlık plastik bardak ve saksı yerine, şeker pancarı nişastasından üretilen 'Polilaktik Asit' (PLA) bardaklara geçmiştir. Bu bardaklar kullanılıp çöpe atıldığında veya toprağa karıştığında mikroorganizmalar tarafından 3 ayda tamamen doğal gübreye ve toprağa dönüşmektedir.",
            isGreen = true,
            explanation = "Fosil kaynaklar yerine tarımsal şeker pancarını ham madde seçen ve kullanım ömrü bitince hiçbir mikroplastik veya çöp bırakmadan yararlı bir organik gübreye dönüşen bu ürün, Yeşil Kimyanın 'Yenilenebilir Hammadde Kullanımı' ve 'Parçalanabilir Tasarım' ilkelerine tam puan uyar."
        )
    )
}
