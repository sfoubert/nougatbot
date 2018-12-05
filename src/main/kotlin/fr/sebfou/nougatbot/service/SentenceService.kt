package fr.sebfou.nougatbot.service

import fr.sebfou.nougatbot.util.TextUtil
import org.springframework.stereotype.Service

/**
 * Created by seb_f on 18/02/2018.
 */
@Service
class SentenceService {

    private final val PART_1 = arrayListOf(
            "Avec",
            "Considérant",
            "Où que nous mène",
            "Eu égard à",
            "Vu",
            "En ce qui concerne",
            "Dans le cas particulier de",
            "Quelle que soit",
            "Du fait de",
            "Tant que durera"
    )

    private final val PART_2 = arrayListOf(
            "la situation",
            "la conjoncture",
            "la crise",
            "l'inertie",
            "l'impasse",
            "l'extrémité",
            "la dégradation des moeurs",
            "la sinistrose",
            "la dualité de la situation",
            "la baisse de confiance"
    )

    private final val PART_3 = arrayListOf(
            "présente",
            "actuelle",
            "qui nous occupe",
            "qui est la nôtre",
            "induite",
            "conjoncturelle",
            "contemporaine",
            "de cette fin de siècle",
            "de la société",
            "de ces derniers temps"
    )

    private final val PART_4 = arrayListOf(
            "il convient de",
            "il faut",
            "on se doit de",
            "il est préférable de",
            "il serait intéressant de",
            "il ne faut pas négliger de",
            "on ne peut se passer de",
            "il est nécessaire de",
            "il serait bon de",
            "il faut de toute urgence"
    )

    private final val PART_5 = arrayListOf(
            "étudier",
            "examiner",
            "ne pas négliger",
            "prendre en considération",
            "anticiper",
            "imaginer",
            "se préoccuper de",
            "s'intéresser à",
            "avoir à l'esprit",
            "se remémorer"
    )

    private final val PART_6 = arrayListOf(
            "toutes les",
            "chacune des",
            "la majorité des",
            "toutes les",
            "l'ensemble des",
            "la somme des",
            "la totalité des",
            "la globalité des",
            "toutes les",
            "certaines"
    )

    private final val PART_7 = arrayListOf(
            "solutions",
            "issues",
            "problématiques",
            "voies",
            "alternatives",
            "solutions",
            "issues",
            "problématiques",
            "voies",
            "alternatives"
    )

    private final val PART_8 = arrayListOf(
            "envisageables",
            "possibles",
            "déjà en notre possession",
            "s'offrant à nous",
            "de bon sens",
            "envisageables",
            "possibles",
            "déjà en notre possession",
            "s'offrant à nous",
            "de bon sens"
    )

    private fun getRandom() = (0..9).shuffled().last()

    fun generateSentence(): String {
        var part1 = PART_1.get(getRandom())
        var part2 = PART_2.get(getRandom())
        var part3 = PART_3.get(getRandom())
        var part4 = PART_4.get(getRandom())
        var part5 = PART_5.get(getRandom())
        var part6 = PART_6.get(getRandom())
        var part7 = PART_7.get(getRandom())
        var part8 = PART_8.get(getRandom())

        var part4part5 = "$part4 $part5"
        if (TextUtil.startWithVowel(part5) && part4.endsWith(" de")) {
            part4 = TextUtil.replaceLast(part4, " de", " d'")
            part4part5 = "$part4$part5"
        }

        return "$part1 $part2 $part3, $part4part5 $part6 $part7 $part8"
    }

}

