package org.move.lang.core.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.ElementPattern
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import org.move.ide.annotator.BUILTIN_TYPE_IDENTIFIERS
import org.move.ide.annotator.PRIMITIVE_TYPE_IDENTIFIERS
import org.move.lang.core.MovePsiPatterns

object PrimitiveTypesCompletionProvider : MoveCompletionProvider() {

    private val primitives: List<String> =
        PRIMITIVE_TYPE_IDENTIFIERS.toList() + BUILTIN_TYPE_IDENTIFIERS.toList()

    override val elementPattern: ElementPattern<out PsiElement>
        get() =
            MovePsiPatterns.nameTypeIdentifier()

    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet,
    ) {
        primitives.forEach {
            var lookup = LookupElementBuilder.create(it).bold()
            if (lookup.lookupString == "vector") {
                lookup = lookup.withInsertHandler(AngleBracketsInsertHandler())
            }
            result.addElement(lookup.withPriority(PRIMITIVE_TYPE_PRIORITY))
//            result.addElement(LookupElementBuilder.create(it).bold().withPriority(PRIMITIVE_TYPE_PRIORITY))
        }
    }
}