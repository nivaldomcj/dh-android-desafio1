import java.time.LocalDateTime

abstract class Professor(codigo: Int, nome: String, sobrenome: String) : Usuario(codigo, nome, sobrenome) {
    private var dataRegistro: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        // deve comparar pelo código do professor, não pelo objeto
        return (other as? Professor)?.let { it.codigo == this.codigo } ?: false
    }

    override fun hashCode(): Int {
        // gerado pela IDE
        return javaClass.hashCode()
    }

    fun obterTempoCasa(): String {
        return obterTempoEntreDatas(dataRegistro, LocalDateTime.now())
    }

    fun obterDataRegistro(): String {
        return obterDataFormatada(dataRegistro)
    }
}