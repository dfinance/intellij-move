package org.move.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.move.ide.MoveIcons
import org.move.lang.core.psi.MoveBindingPat
import org.move.lang.core.psi.MoveLetStatement
import org.move.lang.core.psi.impl.MoveNameIdentifierOwnerImpl
import org.move.lang.core.types.BaseType
import org.move.lang.core.types.TypeVarsMap
import javax.swing.Icon

abstract class MoveBindingPatMixin(node: ASTNode) : MoveNameIdentifierOwnerImpl(node),
                                                    MoveBindingPat {

    override fun getIcon(flags: Int): Icon = MoveIcons.VARIABLE

    override fun resolvedType(typeVars: TypeVarsMap): BaseType? {
        val letStmt = this.parent as? MoveLetStatement ?: return null

        val explicitAnnotation = letStmt.typeAnnotation
        val patternType = when {
            explicitAnnotation != null -> explicitAnnotation.type?.resolvedType(emptyMap())
            else -> {
                val initializerExpr = letStmt.initializer?.expr ?: return null
                initializerExpr.resolvedType(emptyMap())
            }
        }
        if (patternType == null) return null

        return patternType
    }
}
