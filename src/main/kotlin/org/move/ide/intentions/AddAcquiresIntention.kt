package org.move.ide.intentions

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import org.move.ide.annotator.ACQUIRES_BUILTIN_FUNCTIONS
import org.move.lang.core.psi.MoveCallExpr
import org.move.lang.core.psi.MoveFunctionSignature
import org.move.lang.core.psi.MovePsiFactory
import org.move.lang.core.psi.ext.ancestorOrSelf
import org.move.lang.core.psi.ext.typeArguments
import org.move.lang.core.psi.ext.typeNames
import org.move.lang.core.types.StructType

class AddAcquiresIntention : MoveElementBaseIntentionAction<AddAcquiresIntention.Context>() {
    override fun getText(): String = "Add missing 'acquires' declaration"
    override fun getFamilyName(): String = text

    data class Context(
        val functionSignature: MoveFunctionSignature,
        val expectedAcquiresType: StructType
    )

    override fun findApplicableContext(
        project: Project,
        editor: Editor,
        element: PsiElement
    ): Context? {
        val callExpr = element.ancestorOrSelf<MoveCallExpr>() ?: return null
        if (callExpr.referenceName == null
            || callExpr.referenceName !in ACQUIRES_BUILTIN_FUNCTIONS
        ) return null
        if (callExpr.typeArguments.isEmpty()) return null
        val expectedAcquiresType =
            callExpr.typeArguments
                .getOrNull(0)?.type
                ?.resolvedType(emptyMap()) as? StructType ?: return null

        val outFunction = callExpr.containingFunction ?: return null
        val outFunctionSignature = outFunction.functionSignature ?: return null
        val acquiresType = outFunctionSignature.acquiresType

        val context = Context(outFunctionSignature, expectedAcquiresType)
        if (acquiresType == null) return context

        val acquiresTypeNames = acquiresType.typeNames ?: return null
        if (expectedAcquiresType.name() in acquiresTypeNames) return null
        return context
    }

    override fun invoke(project: Project, editor: Editor, ctx: Context) {
        val acquiresType = ctx.functionSignature.acquiresType
        val expectedAcquiresTypeName = ctx.expectedAcquiresType.name()
        if (acquiresType == null) {
            val newFunctionSignature =
                MovePsiFactory(project)
                    .createFunctionSignature("${ctx.functionSignature.text} " +
                                                     "acquires $expectedAcquiresTypeName")
            ctx.functionSignature.replace(newFunctionSignature)
        } else {
            val acquiresTypeText = acquiresType.text.trimEnd(',')
            val newAcquiresType = MovePsiFactory(project)
                .createAcquiresType("$acquiresTypeText, $expectedAcquiresTypeName")
            acquiresType.replace(newAcquiresType)
        }
    }
}
