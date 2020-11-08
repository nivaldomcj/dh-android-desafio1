import java.time.LocalDateTime

class Matricula(val aluno: Aluno, val curso: Curso) {
    private val dataMatricula: LocalDateTime = LocalDateTime.now()
    private var matriculaAtiva: Boolean = true

    override fun toString(): String {
        val dataMatricula = obterDataMatricula()
        val statusMatricula = if (matriculaAtiva) "Matrícula Ativa" else "Matrícula Inativa"

        return "Aluno: `$aluno` | Curso: `$curso` | Data: $dataMatricula | Status: $statusMatricula"
    }

    private fun obterDataMatricula(): String {
        return obterDataFormatada(dataMatricula)
    }

    fun obterStatusMatricula(): Boolean {
        return matriculaAtiva
    }

    fun desativarMatricula() {
        this.matriculaAtiva = false
    }

}