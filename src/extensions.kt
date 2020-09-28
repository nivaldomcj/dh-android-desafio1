// ver: https://kotlinlang.org/docs/reference/extensions.html
// ver: https://stackoverflow.com/questions/14891129/regular-expression-pl-and-pn
fun String.removeCaracteresEspeciais(): String = replace(Regex("[^\\p{L} ]"), "")

// ver: https://stackoverflow.com/questions/52042903/capitalise-every-word-in-string-with-extension-function
fun String.capitalizaTodasPalavras(): String = split(" ").joinToString(" ") { it.capitalize() }
