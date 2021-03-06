package org.move.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.move.lang.core.psi.MoveElementImpl
import org.move.lang.core.psi.MoveType
import org.move.lang.core.types.BaseType
import org.move.lang.core.types.TypeVarsMap

abstract class MoveTypeMixin(node: ASTNode) : MoveElementImpl(node), MoveType {
    override fun resolvedType(typeVars: TypeVarsMap): BaseType? = null
}
