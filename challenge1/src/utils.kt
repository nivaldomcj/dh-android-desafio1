import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun obterDataFormatada(data: LocalDateTime): String {
    // ver: https://dicasdejava.com.br/java-8-como-formatar-localdate-e-localdatetime/
    return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
}

fun obterTempoEntreDatas(dataInicial: LocalDateTime, dataFinal: LocalDateTime): String {
    // ver: https://stackoverflow.com/a/53947199
    // ver: https://developer.android.com/reference/kotlin/java/time/Duration#abs
    val diferenca = Duration.between(dataInicial, dataFinal).abs()

    // ver: https://stackoverflow.com/a/55780162
    val dias = diferenca.toDaysPart()
    val horas = diferenca.toHoursPart()
    val minutos = diferenca.toMinutesPart()
    val segundos = diferenca.toSecondsPart()

    return "$dias dias, $horas horas, $minutos minutos e $segundos segundos"
}