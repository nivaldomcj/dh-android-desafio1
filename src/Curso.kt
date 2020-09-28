class Curso(val codigo: Int, private var nome: String, private var numMaxAlunos: Int) {
    private var professorTitular: ProfessorTitular? = null
    private var professorAdjunto: ProfessorAdjunto? = null
    private val alunosCadastrados: MutableList<Aluno> = mutableListOf()

    init {
        this.nome = nome.removeCaracteresEspeciais().capitalizaTodasPalavras().trim()

        if (codigo < 0)
            println("Alerta: Código não deve ser negativo!")
        if (nome.isBlank())
            println("Alerta: Nome do Curso não deve ser vazio ou apenas com caracteres especiais!")
        if (numMaxAlunos <= 0)
            println("Alerta: Um curso deve ter capacidade de pelo menos um aluno!")
    }

    override fun toString(): String = nome

    override fun equals(other: Any?): Boolean {
        // deve comparar pelo código do curso, não pelo objeto
        return (other as? Curso)?.let { it.codigo == this.codigo } ?: false
    }

    override fun hashCode(): Int {
        // gerado pela IDE
        var result = codigo
        result = 31 * result + nome.hashCode()
        result = 31 * result + numMaxAlunos
        result = 31 * result + (professorTitular?.hashCode() ?: 0)
        result = 31 * result + (professorAdjunto?.hashCode() ?: 0)
        result = 31 * result + alunosCadastrados.hashCode()
        return result
    }

    private fun temVagasDisponiveis(): Boolean {
        return obterNumAlunosCadastrados() < obterNumMaximoAlunos()
    }

    fun obterNumAlunosCadastrados(): Int = alunosCadastrados.size

    fun obterNumMaximoAlunos(): Int = numMaxAlunos

    fun inserirAluno(aluno: Aluno): Boolean {
        if (!temVagasDisponiveis()) {
            println("Erro: Curso `$this` não tem mais vagas disponíveis. Matrícula não realizada!")
            return false
        }
        if (aluno in alunosCadastrados) {
            println("Erro: Aluno `$aluno` já está adicionado a esse curso!")
            return false
        }

        alunosCadastrados.add(aluno)
        println("Aluno `$aluno` adicionado ao Curso `$this`")
        return true
    }

    fun removerAluno(aluno: Aluno): Boolean {
        if (aluno !in alunosCadastrados) {
            println("Erro: Aluno `$aluno` não está presente nos alunos desse curso!")
            return false
        }

        alunosCadastrados.remove(aluno)
        println("Aluno `$aluno` removido com sucesso!")
        return true
    }

    fun definirProfessorTitular(professor: ProfessorTitular) {
        professorTitular = professor
        println("Professor Titular do Curso `$this` definido para: `$professor`")
    }

    fun definirProfessorAdjunto(professor: ProfessorAdjunto) {
        professorAdjunto = professor
        println("Professor Adjunto do Curso `$this` definido para: `$professor`")
    }

    fun obterProfessorTitular(): ProfessorTitular? {
        return professorTitular
    }

    fun obterProfessorAdjunto(): ProfessorAdjunto? {
        return professorAdjunto
    }

}