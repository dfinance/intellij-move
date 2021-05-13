package org.move.lang.core.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class TraitsCompletionProvider: CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val traits = arrayOf("copy", "store", "drop", "key")
        for (trait in traits) {
            val element = LookupElementBuilder.create(trait)
            result.addElement(PrioritizedLookupElement.withPriority(element, KEYWORD_PRIORITY))
        }
    }
}
