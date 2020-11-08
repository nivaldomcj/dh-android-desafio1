import java.time.LocalDateTime

fun testeObterTempoEntreDatas() {
    val data1 = LocalDateTime.of(2020, 8, 1, 3, 15, 43)
    val data2 = LocalDateTime.of(2020, 9, 5, 1, 35, 52)

    println("$data1 ~> $data2")
    println(obterTempoEntreDatas(data1, data2))
}

fun testeObterDataFormatada() {
    val data = LocalDateTime.of(2020, 8, 1, 3, 15, 43)

    println("$data")
    println(obterDataFormatada(data))
}

fun testeCapitalizaTodasPalavras() {
    val texto = "mouse teclado cadeira retrovisor parachoque"

    println(texto)
    println(texto.capitalizaTodasPalavras())
}

fun testeRemoveCaracteresEspeciais() {
    val texto = "Joã!@!$!#%#&%$#&!@$!!!#!@#!%#%@$%%$@#%!#o Si@#!@$~~`l`--===veir!@#a"

    println(texto)
    println(texto.removeCaracteresEspeciais())
}

fun testeUsuariosIguais() {
    println("Dois alunos com o mesmo código:")
    val aluno1 = Aluno(150, "Nivaldo", "Carvalho")
    val aluno2 = Aluno(150, "Maria", "Santos")
    println(aluno1)
    println(aluno2)
    println(aluno1 == aluno2)
    println("---")

    println("Dois professores adjuntos com o mesmo código:")
    val adjunto1 = ProfessorAdjunto(170, "João", "Silva", 50)
    val adjunto2 = ProfessorAdjunto(170, "Adriano", "Santos", 130)
    println(adjunto1)
    println(adjunto2)
    println(adjunto1 == adjunto2)
    println("---")

    println("Dois professores titulares com o mesmo código:")
    val titular1 = ProfessorTitular(170, "Carlos", "Padilha", "Engenharia")
    val titular2 = ProfessorTitular(170, "Maria", "Padilha", "Direito")
    println(titular1)
    println(titular2)
    println(titular1 == titular2)
    println("---")

    println("Dois professores diferentes com o mesmo código:")
    val professores: List<Professor> = listOf(adjunto1, titular1)
    println(professores)
    println(professores[0] == professores[1])
    println("---")

    println("Comparação de duas instâncias que herdam de Usuario:")
    val aluno3 = Aluno(123, "Larissa", "Manoela")
    val titular3 = ProfessorTitular(123, "Joana", "Santos", "Programação")
    println("$aluno3 é ${aluno3.javaClass}")
    println("$titular3 é ${titular3.javaClass}")

    // Aluno ~> Usuário
    println("$aluno3 especializa ${aluno3.javaClass.superclass}")

    // ProfessorTitular ~> Professor ~> Usuário
    println("$titular3 especializa ${titular3.javaClass.superclass.superclass}")

    // embora sejam da mesma classe pai, não devem ser comparáveis
    println("$aluno3 == $titular3? ${aluno3.equals(titular3)}")
    println("Códigos são iguais? ${aluno3.codigo == titular3.codigo}")

    println("---")
}

fun testeErrosAlertas() {
    println("Dois professores titulares com dados inválidos:")
    val erroProfTitular1 = ProfessorTitular(-500, "", "", "")
    println(erroProfTitular1)
    val erroProfTitular2 = ProfessorTitular(-2000, "##!@#!@#$!$", "!@#$!%!@##$%#%~{}", "")
    println(erroProfTitular2)
    println("***")

    println("Dois professores adjuntos com dados inválidos:")
    val erroProfAdjunto1 = ProfessorAdjunto(-500, "", "", -200)
    println(erroProfAdjunto1)
    val erroProfAdjunto2 = ProfessorAdjunto(-2000, "##!@#!@#$!$", "!@#$!%!@##$%#%~{}", -500)
    println(erroProfAdjunto2)
    println("***")

    println("Aluno com dados inválidos:")
    val erroAluno = Aluno(-600, "!@#!!FF", "                ")
    println(erroAluno)
    println("***")

    val dhm = DigitalHouseManager()

    println("Inserção de dois cursos com dados inválidos:")
    dhm.inserirCurso(-500, "!#@$!#$%#$", -50)
    dhm.inserirCurso(-300, "", -245)
    println("***")

    println("Inserção de dois professores com dados inválidos:")
    dhm.inserirProfessorTitular(-450, "!!#!!#!", "!@$!#$!$!$", "!$!$#$   !")
    dhm.inserirProfessorAdjunto(-41561, "", "", -4444)
    println("***")

    println("Inserção de dois alunos com dados inválidos:")
    dhm.inserirAluno(-46545, "!#@$!@$!%!@$", "-*--*-/+.-/")
    dhm.inserirAluno(0, "", "")
    println("***")

    println("Matrícula de dois alunos com dados inválidos:")
    dhm.matricularAluno(-18978, -74410)
    dhm.matricularAluno(0, 0)
    println("***")

    println("Exclusão de dois cursos com códigos inexistentes:")
    dhm.excluirCurso(-11111)
    dhm.excluirProfessor(-11111)
    println("***")

    println("Duas alocações de professores com dados incorretos:")
    dhm.alocarProfessores(-300, -41561, -450)
    dhm.alocarProfessores(-300, 0, 0)
    println("***")

    println("Desmatrícula de aluno com dados incorretos:")
    dhm.desmatricularAluno(0, 0)
    println("***")

    println("Exibição de todos os dados registrados:")
    dhm.exibirTodasMatriculas()
    dhm.exibirTodosCursos()
    dhm.exibirTodosAlunos()
    dhm.exibirTodosProfessores()
    println("***")
}

fun testeSucesso() {
    val manager = DigitalHouseManager()

    println("Inserção de dois alunos:")
    manager.inserirAluno(1, "João", "Santos")
    manager.inserirAluno(2, "Maria", "Silva")
    manager.exibirTodosAlunos()
    println("***")

    println("Inserção de dois professores:")
    manager.inserirProfessorAdjunto(1, "Carlos", "Alberto", 50)
    manager.inserirProfessorTitular(2, "José", "Paes", "Desenvolvimento")
    manager.exibirTodosProfessores()
    println("***")

    println("Inserção de um curso:")
    manager.inserirCurso(2, "Programação", 1)
    manager.exibirTodosCursos()
    println("***")

    println("Alocação de professores ao curso:")
    manager.alocarProfessores(2, 2, 1)
    println("***")

    println("Matrícula de dois alunos (um deve falhar):")
    manager.matricularAluno(1, 2)
    manager.matricularAluno(2, 2)       // deve falhar
    manager.exibirTodasMatriculas()
    println("***")

    println("Tentativa de exclusão de aluno:")
    manager.excluirAluno(1)
    manager.exibirMatriculasAtivasAluno(1)
    println("***")

    println("Desmatrícula de aluno:")
    manager.desmatricularAluno(1, 2)
    manager.exibirTodasMatriculas()
    println("***")

    println("Matrícula de aluno:")
    manager.matricularAluno(2, 2)       // deve funcionar
    manager.exibirTodasMatriculas()
    println("***")

    println("Exclusão de professor:")
    manager.excluirProfessor(1)
    manager.exibirTodosProfessores()
    println("***")

    println("Exclusão de um curso com matrículas ativas:")
    manager.exibirTodosCursos()
    manager.exibirTodasMatriculas()
    manager.excluirCurso(2)
    println("***")
}

fun main() {
    testeObterTempoEntreDatas()
    testeObterDataFormatada()

    testeCapitalizaTodasPalavras()
    testeRemoveCaracteresEspeciais()

    testeUsuariosIguais()

    testeErrosAlertas()
    testeSucesso()
}