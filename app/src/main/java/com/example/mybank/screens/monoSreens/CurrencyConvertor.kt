package com.example.mybank.screens.monoSreens

import java.io.Serializable

class CurrencyConvertor(code: String) {
    val currencyCodes = mapOf(
        "980" to Triple("UAH", "Українська гривня","https://cdn.pixabay.com/animation/2022/09/16/16/43/16-43-27-842_512.gif" ),
        "840" to Triple("USD", "Долар США","https://usagif.com/wp-content/uploads/flag-america-usa.gif"), //
        "978" to Triple("EUR", "Євро", "https://media.tenor.com/r34P44mokakAAAAi/eu-flag.gif"),//
        "826" to Triple("GBP", "Фунт стерлінгів Великобританії","https://upload.wikimedia.org/wikipedia/commons/2/2d/Animated-Flag-United-Kingdom.gif"),//
        "392" to Triple("JPY", "Японська ієна","https://1.bp.blogspot.com/-NNTn9C1vlhE/YDf-b6ORWdI/AAAAAAAA4Sw/6oLIomsgVvI4sdOvHzIJMRsCCBRedH1rQCLcBGAsYHQ/s0/Flag_of_Japan.gif"),//
        "756" to Triple("CHF", "Швейцарський франк","https://cdn.pixabay.com/animation/2022/09/13/17/55/17-55-51-8_512.gif"),//
        "156" to Triple("CNY", "Китайський юань женьміньбі","https://upload.wikimedia.org/wikipedia/commons/f/f6/Flowing_Flag_of_the_People%27s_Republic_of_China_2.gif?20090906070401"),//
        "784" to Triple("AED", "Дірхам ОАЕ","https://1.bp.blogspot.com/-j92PeYvL9xs/YEaODSYFkhI/AAAAAAAA5E0/uQct3JLU_8krrw9OOwx0aqAyIDUGBk_zACLcBGAsYHQ/s0/Flag_of_the_United_Arab_Emirates.gif"),//
        "971" to Triple("AFN", "Афганський афгані","https://upload.wikimedia.org/wikipedia/commons/7/7f/Animated-Flag-Afghanistan.gif"),//
        "008" to Triple("ALL", "Албанський лек","https://upload.wikimedia.org/wikipedia/commons/1/1b/Animated-Flag-Albania.gif"),//
        "051" to Triple("AMD", "Вірменський драм","https://1.bp.blogspot.com/-vqfJf1Hj8Ow/YDBVHpeT67I/AAAAAAAA3uc/azBncDDN0GIsJvsEcnRkxWg56cAvUbgiQCLcBGAsYHQ/s0/Flag_of_Armenia.gif"),//
        "973" to Triple("AOA", "Ангольська кванза","https://cdn.pixabay.com/animation/2022/07/31/19/29/19-29-29-919_512.gif"), //
        "032" to Triple("ARS", "Аргентинський песо","https://cdn.pixabay.com/animation/2022/08/01/03/42/03-42-52-_512.gif"),
        "036" to Triple("AUD", "Австралійський долар","https://cdn.pixabay.com/animation/2022/08/01/03/42/03-42-57-408_512.gif"),
        "944" to Triple("AZN", "Азербайджанський манат","https://cdn.pixabay.com/animation/2022/08/01/03/43/03-43-01-323_512.gif"),
        "050" to Triple("BDT", "Бангладеська така","https://cdn.pixabay.com/animation/2022/08/01/03/43/03-43-08-674_512.gif"),
        "975" to Triple("BGN", "Болгарський лев","https://cdn.pixabay.com/animation/2022/08/05/18/25/18-25-00-385_512.gif"),
        "048" to Triple("BHD", "Бахрейнський динар","https://cdn.pixabay.com/animation/2022/08/01/03/43/03-43-05-651_512.gif"),
        "108" to Triple("BIF", "Бурундійський франк","https://cdn.pixabay.com/animation/2022/08/07/20/19/20-19-26-593_512.gif"),
        "096" to Triple("BND", "Брунейський долар","https://cdn.pixabay.com/animation/2022/08/05/18/24/18-24-57-534_512.gif"),
        "068" to Triple("BOB", "Болівійський болівіано","https://1.bp.blogspot.com/-rKZjXnU7z9E/YDF6BaA6nyI/AAAAAAAA31w/gLdMiaIYGzg9MqzMLpRlJZzZ_xKe6bRTgCLcBGAsYHQ/s0/Flag_of_Bolivia.gif"),
        "986" to Triple("BRL", "Бразильський реал","https://cdn.pixabay.com/animation/2022/08/05/18/24/18-24-53-297_512.gif"),
        "072" to Triple("BWP", "Ботсванська пула","https://cdn.pixabay.com/animation/2022/08/05/18/24/18-24-48-409_512.gif"),
        "933" to Triple("BYN", "Білоруський рубль","https://pictures.pibig.info/uploads/posts/2023-04/1681603673_pictures-pibig-info-p-chernii-kvadrat-risunok-krasivo-65.jpg"),
        "124" to Triple("CAD", "Канадський долар","https://1.bp.blogspot.com/-L2ZbZpmSsXc/YDLeCszTS1I/AAAAAAAA36M/QrLJj1i50H8JRA-PAT7NAqNHxwfde7gUgCLcBGAsYHQ/s0/Flag_of_Canada.gif"),
        "976" to Triple("CDF", "Конголезький франк","https://cdn.pixabay.com/animation/2022/08/07/20/20/20-20-09-831_512.gif"),
        "152" to Triple("CLP", "Чилійський песо","https://cdn.pixabay.com/animation/2022/08/07/20/19/20-19-50-911_512.gif"),
        "170" to Triple("COP", "Колумбійський песо","https://1.bp.blogspot.com/-Jhk-wmlfDhw/YDP6Ig10FqI/AAAAAAAA388/0ewN64BTvRUQ1BWeN0aeCqSwcGCDdlAqwCLcBGAsYHQ/s0/Flag_of_Colombia.gif"),
        "188" to Triple("CRC", "Костариканський колон","https://1.bp.blogspot.com/-JeFj-PTYg4U/YDQTQ5WjkAI/AAAAAAAA3_E/JICd9v_J3FcBXfe3j9laHBC2LfGzTaDlwCLcBGAsYHQ/s0/Flag_of_Costa_Rica.gif"),
        "192" to Triple("CUP", "Кубинський песо","https://3.bp.blogspot.com/-nzSe3tiIvsU/XtWbUI16lwI/AAAAAAAA1tA/R38ZCTeaJUIpltJD02Osr-9IQkdHdF2vwCLcBGAsYHQ/s1600/Cuba_flag_with_coat_of_arms.gif"),
        "203" to Triple("CZK", "Чеська крона","https://upload.wikimedia.org/wikipedia/commons/5/57/Animated-Flag-Czech.gif"),
        "262" to Triple("DJF", "Джибутійський франк","https://1.bp.blogspot.com/-L8KACxTDzjg/YDSsdC_LVZI/AAAAAAAA4B8/L3uOM6GjOPkQFcCRSM37DoC1DKW7rK0LwCLcBGAsYHQ/s0/Flag_of_Djibouti.gif"),
        "208" to Triple("DKK", "Данська крона","https://1.bp.blogspot.com/-4dvCp9o_N-U/YDQvU25lFlI/AAAAAAAA4BU/acwGCdpmkJI7yZsquUHqAWXfWyXpBARdQCLcBGAsYHQ/s0/Flag_of_Denmark.gif"),
//        "012" to Pair("DZD", "Алжирський динар"),
//        "818" to Pair("EGP", "Єгипетський фунт"),
//        "230" to Pair("ETB", "Ефіопський бір"),
//        "981" to Pair("GEL", "Грузинський ларі"),
//        "936" to Pair("GHS", "Ганський сиді"),
//        "270" to Pair("GMD", "Гамбійський далас"),
//        "324" to Pair("GNF", "Гвінейський франк"),
//        "344" to Pair("HKD", "Гонконгський долар"),
//        "191" to Pair("HRK", "Хорватська куна"),
//        "348" to Pair("HUF", "Угорський форинт"),
//        "360" to Pair("IDR", "Індонезійська рупія"),
//        "376" to Pair("ILS", "Ізраїльський шекель"),
//        "356" to Pair("INR", "Індійська рупія"),
//        "368" to Pair("IQD", "Іракський динар"),
//        "364" to Pair("IRR", "Іранський ріал"),
//        "352" to Pair("ISK", "Ісландська крона"),
//        "400" to Pair("JOD", "Йорданський динар"),
//        "404" to Pair("KES", "Кенійський шилінг"),
//        "417" to Pair("KGS", "Киргизький сом"),
//        "116" to Pair("KHR", "Камбоджійський рієль"),
//        "408" to Pair("KPW", "Північно-корейська вона (КНДР)"),
//        "410" to Pair("KRW", "Південно-корейська вона (Корея)"),
//        "414" to Pair("KWD", "Кувейтський динар"),
//        "398" to Pair("KZT", "Казахстанський тенге"),
//        "418" to Pair("LAK", "Лаоський кіп"),
//        "422" to Pair("LBP", "Ліванський фунт"),
//        "144" to Pair("LKR", "Шрі-ланкійська рупія"),
//        "434" to Pair("LYD", "Лівійський динар"),
//        "504" to Pair("MAD", "Марокканський дирхам"),
//        "498" to Pair("MDL", "Молдовський лей"),
//        "969" to Pair("MGA", "Малагасійський аріарі"),
//        "807" to Pair("MKD", "Македонський денар"),
//        "496" to Pair("MNT", "Монгольський тугрик"),
//        "478" to Pair("MRO", "Мавританська вугія"),
//        "480" to Pair("MUR", "Маврикійська рупія"),
//        "462" to Pair("MVR", "Мальдівська руфія"),
//        "454" to Pair("MWK", "Малавійська квача"),
//        "484" to Pair("MXN", "Мексиканське песо"),
//        "458" to Pair("MYR", "Малайзійський рінггіт"),
//        "943" to Pair("MZN", "Мозамбікський метикал"),
//        "516" to Pair("NAD", "Намібійський долар"),
//        "566" to Pair("NGN", "Нігерійська наїра"),
//        "558" to Pair("NIO", "Нікарагуанська кордоба"),
//        "578" to Pair("NOK", "Норвезька крона"),
//        "524" to Pair("NPR", "Непальська рупія"),
//        "554" to Pair("NZD", "Новозеландський долар"),
//        "512" to Pair("OMR", "Оманський ріал"),
//        "604" to Pair("PEN", "Перуанська сіль"),
//        "608" to Pair("PHP", "Філіппінське песо"),
//        "586" to Pair("PKR", "Пакистанська рупія"),
        "985" to Triple("PLN", "Польський злотий", "https://cdn.pixabay.com/animation/2022/09/06/03/13/03-13-33-893_512.gif"),
//        "600" to Pair("PYG", "Парагвайський гуарані"),
//        "634" to Pair("QAR", "Катарський ріал"),
//        "946" to Pair("RON", "Новий румунський лей"),
//        "941" to Pair("RSD", "Сербський динар"),
//        "682" to Pair("SAR", "Саудівський ріал"),
//        "690" to Pair("SCR", "Сейшельська рупія"),
//        "938" to Pair("SDG", "Суданський фунт"),
//        "752" to Pair("SEK", "Шведська крона"),
//        "702" to Pair("SGD", "Сінгапурський долар"),
//        "694" to Pair("SLL", "Сьєрра-леонський леоне"),
//        "706" to Pair("SOS", "Сомалійський шилінг"),
//        "968" to Pair("SRD", "Суринамський долар"),
//        "760" to Pair("SYP", "Сирійський фунт"),
//        "748" to Pair("SZL", "Свазілендський ліланген"),
//        "764" to Pair("THB", "Таїландський бат"),
//        "972" to Pair("TJS", "Таджицький сомоні"),
//        "795" to Pair("TMT", "Туркменський манат"),
//        "788" to Pair("TND", "Туніський динар"),
//        "949" to Pair("TRY", "Нова турецька ліра"),
//        "901" to Pair("TWD", "Тайванський долар"),
//        "834" to Pair("TZS", "Танзанійський шилінг"),
//        "800" to Pair("UGX", "Угандійський шилінг"),
//        "858" to Pair("UYU", "Уругвайський песо"),
//        "860" to Pair("UZS", "Узбецька сум"),
//        "937" to Pair("VEF", "Венесуельський болівар"),
//        "704" to Pair("VND", "В'єтнамський донг"),
//        "950" to Pair("XAF", "Франк КФА (Центральна Африка)"),
//        "960" to Pair("XDR", "СПЗ"),
//        "952" to Pair("XOF", "Франк КФА (Західна Африка)"),
//        "886" to Pair("YER", "Єменський ріал"),
//        "710" to Pair("ZAR", "Південно-Африканський Ренд"),
//        "894" to Pair("ZMK", "Замбійська квача"),
        // Додайте інші коди валют та їх абревіатури і повні назви сюди
    )

    fun getCurrencyDetails(code: String): Triple<String, String, String>? {
        return currencyCodes[code]
    }

    // Приклад використання:
    val currencyDetails = getCurrencyDetails(code)

}


