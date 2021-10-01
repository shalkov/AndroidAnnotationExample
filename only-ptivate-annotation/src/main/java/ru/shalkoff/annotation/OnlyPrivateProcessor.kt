package ru.shalkoff.annotation

import com.google.auto.service.AutoService
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class OnlyPrivateProcessor : KotlinAbstractProcessor() {

    private val myAnnotation = OnlyPrivate::class.java

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {

        roundEnv.getElementsAnnotatedWith(myAnnotation).forEach { annotatedElement ->
            if (annotatedElement.kind == ElementKind.METHOD) {
                if (!annotatedElement.modifiers.contains(Modifier.PRIVATE)) {
                    errorKindMessage("The method ${annotatedElement.simpleName} can be private only")
                }
            } else {
                errorKindMessage("Only methods can be marked with this annotation.")
            }
        }
        return true
    }

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(myAnnotation.canonicalName)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    private fun errorKindMessage(message: String) {
        messager.printMessage(Diagnostic.Kind.ERROR, message)
    }
}