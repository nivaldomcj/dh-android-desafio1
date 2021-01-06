class DigitalHouseManager {
    private val alunos: MutableList<Aluno> = mutableListOf()
    private val cursos: MutableList<Curso> = mutableListOf()
    private val matriculas: MutableList<Matricula> = mutableListOf()
    private val professores: MutableList<Professor> = mutableListOf()

    // ---------------------------------------------------------------------------------------------

    private fun cursoExiste(codigo: Int): Boolean {
        return cursos.any { it.codigo == codigo }
    }

    private fun obterCursoPorCodigo(codigo: Int): Curso? {
        return cursos.firstOrNull { it.codigo == codigo }
    }

    fun inserirCurso(codigo: Int, nome: String, numMaxAlunos: Int) {
        if (cursoExiste(codigo)) {
            println("Erro: Operação cancelada! O código `$codigo` já existe para um curso!")
        } else {
            if (cursos.add(Curso(codigo, nome, numMaxAlunos)))
                println("Curso registrado com sucesso com o codigo `$codigo`!")
            else
                println("Erro: Não foi possível registrar o curso!")
        }
    }

    fun excluirCurso(codigo: Int) {
        obterCursoPorCodigo(codigo)?.let {
            val matriculasAtivas = matriculas.filter { matricula -> matricula.curso == it }

            if (matriculasAtivas.isNotEmpty()) {
                println("Alerta: Serão desativadas `${matriculasAtivas.size}` matrículas desse curso")
                matriculasAtivas.forEach(Matricula::desativarMatricula)
            }

            this.cursos.remove(it)
            println("O curso com código `$codigo` foi removido com sucesso!")
            return
        }

        println("Erro: O curso não está presente na lista de cursos!")
    }

    fun exibirTodosCursos() {
        cursos.forEach {
            val numAlunosRegistrados = it.obterNumAlunosCadastrados()
            val numMaximoAlunos = it.obterNumMaximoAlunos()

            println("Curso: $it | Número de Alunos: $numAlunosRegistrados | Capacidade: $numMaximoAlunos")
        }.also {
            println("Total de Cursos: ${cursos.size}")
        }
    }

    // ---------------------------------------------------------------------------------------------

    private fun professsorExiste(codigo: Int): Boolean {
        return professores.any { it.codigo == codigo }
    }

    private fun obterProfessorPorCodigo(codigo: Int): Professor? {
        return professores.firstOrNull { it.codigo == codigo }
    }

    private fun inserirProfessor(codigo: Int, professor: Professor) {
        if (professsorExiste(codigo)) {
            println("Erro: Operação cancelada! O código `$codigo` já existe para um professor!")
        } else {
            if (professores.add(professor))
                println("Professor `$professor` registrado com sucesso!")
            else
                println("Erro: Não foi possível registrar o professor!")
        }
    }

    fun inserirProfessorAdjunto(codigo: Int, nome: String, sobrenome: String, qtdHorasMonitoria: Int) {
        inserirProfessor(codigo, ProfessorAdjunto(codigo, nome, sobrenome, qtdHorasMonitoria))
    }

    fun inserirProfessorTitular(codigo: Int, nome: String, sobrenome: String, especialidade: String) {
        inserirProfessor(codigo, ProfessorTitular(codigo, nome, sobrenome, especialidade))
    }

    private fun obterCursosProfessor(professor: Professor): List<Curso> {
        return if (professor is ProfessorTitular)
            cursos.filter { it.obterProfessorTitular() == professor }
        else
            cursos.filter { it.obterProfessorAdjunto() == professor }
    }

    fun excluirProfessor(codigo: Int) {
        obterProfessorPorCodigo(codigo)?.let {
            professores.remove(it)

            // alerta ao usuário que ele precisa alocar outro professor pro curso
            // já que ele está excluindo um professor da lista de cadastrados
            val cursosProfessor = obterCursosProfessor(it)
            if (cursosProfessor.isNotEmpty()) {
                println("Alerta: Cursos necessários de realocar um novo professor:")
                cursosProfessor.forEach(::println)
            }

            println("Professor `$it` foi removido com sucesso!")
            return
        }

        println("Erro: O professor não está presente na lista de professores!")
    }

    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int, codigoProfessorAdjunto: Int) {
        val curso = obterCursoPorCodigo(codigoCurso)
        val titular = obterProfessorPorCodigo(codigoProfessorTitular)
        val adjunto = obterProfessorPorCodigo(codigoProfessorTitular)

        // só devemos continuar se todos estão devidamente registrados
        if (curso == null) {
            println("Erro: Não foi encontrado nenhum curso com o código $codigoCurso")
            return
        }
        if (titular == null) {
            println("Erro: Não existe nenhum professor com o código $codigoProfessorTitular cadastrado!")
            return
        }
        if (adjunto == null) {
            println("Erro: Não existe nenhum professor com o código $codigoProfessorAdjunto cadastrado!")
            return
        }

        // verifica se os professores selecionados estão corretos
        if (titular !is ProfessorTitular) {
            println("Erro: Professor $titular selecionado não é um Professor Titular!")
            return
        }
        if (adjunto !is ProfessorAdjunto) {
            println("Erro: Professor $adjunto selecionado não é um Professor Adjunto!")
            return
        }

        // aloca os professores no curso
        curso.definirProfessorTitular(titular)
        curso.definirProfessorAdjunto(adjunto)
    }

    fun exibirTodosProfessores() {
        professores.forEach {
            // comum para todos os professores
            val dataRegistro = it.obterDataRegistro()
            val tempoCasa = it.obterTempoCasa()

            // específico de cada professor
            if (it is ProfessorTitular)
                println("$it [Titular] (Esp: ${it.obterEspecialidade()}) - Desde $dataRegistro ($tempoCasa)")
            if (it is ProfessorAdjunto)
                println("$it [Adjunto] (Horas: ${it.obterHorasMonitoria()}) - Desde $dataRegistro ($tempoCasa)")
        }.also {
            println("Total de Professores: ${professores.size}")
        }
    }

    // ---------------------------------------------------------------------------------------------

    private fun alunoExiste(codigo: Int): Boolean {
        return alunos.any { it.codigo == codigo }
    }

    private fun obterAlunoPorCodigo(codigo: Int): Aluno? {
        return alunos.firstOrNull { it.codigo == codigo }
    }

    fun inserirAluno(codigo: Int, nome: String, sobrenome: String) {
        if (alunoExiste(codigo)) {
            println("Erro: Operação cancelada! O código `$codigo` já existe para um aluno!")
        } else {
            if (alunos.add(Aluno(codigo, nome, sobrenome)))
                println("Aluno registrado com sucesso com o código `$codigo`!")
            else
                println("Erro: Não foi possível registrar o aluno!")
        }
    }

    fun excluirAluno(codigoAluno: Int) {
        if (!alunoExiste(codigoAluno)) {
            println("Erro: Aluno `$codigoAluno` não está registrado!")
            return
        }

        if (existeMatriculaAtiva(codigoAluno)) {
            println("Erro: Aluno `$codigoAluno` está matriculado em um ou mais cursos. Desmatricule-o primeiro!")
            return
        }

        obterAlunoPorCodigo(codigoAluno)?.let {
            alunos.remove(it)
            println("Aluno `$it` foi removido com sucesso!")
        }
    }

    fun exibirTodosAlunos() {
        alunos.forEach(::println).also { println("Total de Alunos: ${alunos.size}") }
    }

    // ---------------------------------------------------------------------------------------------

    private fun existeMatriculaAtiva(codigoAluno: Int): Boolean {
        return matriculas.any { it.obterStatusMatricula() && it.aluno.codigo == codigoAluno }
    }

    private fun existeMatriculaAtiva(codigoAluno: Int, codigoCurso: Int): Boolean {
        return matriculas.any { matricula ->
            matricula.obterStatusMatricula()
                    && matricula.aluno.codigo == codigoAluno
                    && matricula.curso.codigo == codigoCurso
        }
    }

    fun exibirMatriculasAtivasAluno(codigoAluno: Int) {
        obterAlunoPorCodigo(codigoAluno)?.let { aluno ->
            matriculas.filter { matricula -> matricula.aluno == aluno }.forEach(::println)
        }
    }

    private fun obterMatriculaPorCodigo(codigoAluno: Int, codigoCurso: Int): Matricula? {
        // matrículas inativas são mantidas apenas para histórico?
        return matriculas.firstOrNull { matricula ->
            matricula.obterStatusMatricula()
                    && matricula.aluno.codigo == codigoAluno
                    && matricula.curso.codigo == codigoCurso
        }
    }

    fun matricularAluno(codigoAluno: Int, codigoCurso: Int) {
        val aluno = obterAlunoPorCodigo(codigoAluno)
        val curso = obterCursoPorCodigo(codigoCurso)

        // só devemos continuar se o aluno e o curso existirem na base
        if (aluno == null || curso == null) {
            println("Erro: Código do Aluno e/ou Código do Curso informado não existe!")
            return
        }

        // não podemos matricular um aluno com matrícula ativa
        if (existeMatriculaAtiva(codigoAluno, codigoCurso)) {
            println("Erro: O aluno `$codigoAluno` já está matriculado no curso `$codigoCurso`")
            return
        }

        // matriculará o aluno apenas se o curso tiver capacidade suficiente
        if (curso.inserirAluno(aluno))
            matriculas.add(Matricula(aluno, curso))
    }

    fun desmatricularAluno(codigoAluno: Int, codigoCurso: Int) {
        val matricula = obterMatriculaPorCodigo(codigoAluno, codigoCurso)

        // só devemos continuar se a matrícula existe na base e está ativa
        if (matricula == null) {
            println("Erro: O aluno (Cód=$codigoAluno) não está matriculado no curso (Cód=$codigoCurso)")
            return
        }

        // desativa a matrícula do aluno e remove-o dos alunos do curso
        matricula.curso.removerAluno(matricula.aluno)
        matricula.desativarMatricula()
    }

    fun exibirTodasMatriculas() {
        matriculas.forEach(::println).also { println("Total de Matrículas: ${matriculas.size}") }
    }
}