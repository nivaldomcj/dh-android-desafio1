class ProfessorTitular(codigo: Int,
                       nome: String,
                       sobrenome: String,
                       private var especialidade: String) : Professor(codigo, nome, sobrenome) {
    init {
        especialidade = especialidade.removeCaracteresEspeciais().capitalizaTodasPalavras().trim()

        if (especialidade.isBlank())
            println("Alerta: A especialidade do Professor Titular deve ser preenchida!")
    }

    fun obterEspecialidade(): String = especialidade

}