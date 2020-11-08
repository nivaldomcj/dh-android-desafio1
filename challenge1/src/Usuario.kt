abstract class Usuario(val codigo: Int,
                       private var nome: String,
                       private var sobrenome: String) {
    init {
        nome = nome.removeCaracteresEspeciais().trim().capitalize()
        sobrenome = sobrenome.removeCaracteresEspeciais().trim().capitalize()

        if (codigo < 0)
            println("Alerta: Código não deve ser negativo!")
        if (nome.isBlank() || sobrenome.isBlank())
            println("Alerta: Nome e/ou Sobrenome não devem ser vazios ou apenas com caracteres especiais!")
    }

    override fun toString(): String = "$nome $sobrenome"

}