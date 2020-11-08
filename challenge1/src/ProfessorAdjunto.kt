class ProfessorAdjunto(codigo: Int,
                       nome: String,
                       sobrenome: String,
                       private val horasMonitoria: Int) : Professor(codigo, nome, sobrenome) {
    init {
        if (horasMonitoria <= 0)
            println("Alerta: Professor Adjunto deve ter ao menos 1 hora disponível para monitoria!")
    }

    fun obterHorasMonitoria(): Int = horasMonitoria
}