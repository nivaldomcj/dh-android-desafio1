class Aluno(codigo: Int, nome: String, sobrenome: String) : Usuario(codigo, nome, sobrenome) {
    override fun equals(other: Any?): Boolean {
        // deve comparar pelo código do usuário, e não pelo objeto
        return (other as? Aluno)?.codigo == this.codigo
    }

    override fun hashCode(): Int {
        // gerado pela IDE
        return javaClass.hashCode()
    }
}