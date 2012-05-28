package org.waman.functionplotter

import org.jfree.data.function.Function2D

class ScriptFunction2D implements Function2D{

    static final GroovyShell SHELL = new GroovyShell()

    Script functionScript

    ScriptFunction2D(String function){
        def expr = """
import static java.lang.Math.*
import static org.apache.commons.math3.special.Beta.*
import static org.apache.commons.math3.special.Gamma.*
import static org.apache.commons.math3.special.Erf.*
$function"""
        this.functionScript = SHELL.parse(expr)
    }

    double getValue(double x){
        functionScript.x = x
        return functionScript.run()
    }
}