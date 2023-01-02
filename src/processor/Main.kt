package processor

import java.util.*

val scanner: Scanner = Scanner(System.`in`)

fun main() {
    InputProcessor.run()
}

object InputProcessor {
    fun run() {
        while (true) {
            println(
                "1. Add matrices\n" +
                        "2. Multiply matrix by a constant\n" +
                        "3. Multiply matrices\n" +
                        "4. Transpose matrices\n" +
                        "5. Calculate a determinant\n" +
                        "6. Inverse matrix\n" +
                        "0. Exit\n" +
                        "Your choice: "
            )

            when (scanner.nextLine().toInt()) {
                1 -> addMatrices()
                2 -> multiplyByConst()
                3 -> multiplyMatrices()
                4 -> transponseMatrices()
                5 -> calcDeterminant()
                6 -> inverseMatrix()
                0 -> break
            }
        }
    }

    private fun addMatrices() {
        val matrixA = enterMatrix("Enter size of first matrix: ", "Enter first matrix:")
        val matrixB = enterMatrix("Enter size of second matrix: ", "Enter second matrix:")

        println("The result is:")
        matrixA.add(matrixB)?.let { printMatrix(it) }
    }

    private fun multiplyByConst() {
        val matrix = enterMatrix()
        val c = enterConstant()

        println("The result is:")
        val result: Matrix = matrix.multiplyByConst(c)
        printMatrix(result)
    }

    private fun multiplyMatrices() {
        val matrixA = enterMatrix("Enter size of first matrix: ", "Enter first matrix:")
        val matrixB = enterMatrix("Enter size of second matrix: ", "Enter second matrix:")

        println("The result is:")
        val result = matrixA.multiply(matrixB)
        result?.let { printMatrix(it) }
    }

    private fun transponseMatrices() {
        println(
            "1. Main diagonal\n" +
                    "2. Side diagonal\n" +
                    "3. Vertical line\n" +
                    "4. Horizontal line\n" +
                    "Your choice: "
        )
        val choice = scanner.nextLine().toInt()

        val matrix = enterMatrix()

        val transponsed: Matrix? = when (choice) {
            1 -> Transformator.transponseMainDiagonal(matrix)
            2 -> Transformator.transponseSideDiagonal(matrix)
            3 -> Transformator.transponseVertical(matrix)
            4 -> Transformator.transponseHorizontal(matrix)
            else -> null
        }

        if (transponsed != null) {
            printMatrix(transponsed)
        }

    }

    private fun calcDeterminant() {
        val matrix = enterMatrix()
        println(
            "The result is:\n" +
                    DeterminantCalculator.getForNDimMatrix(matrix)
        )
    }

    private fun inverseMatrix() {
        val matrix = enterMatrix()
        println("The result is:")
        val inversedMatrix = Transformator.inverse(matrix)
        if (inversedMatrix == null) println("This matrix doesn't have an inverse.")
        else printMatrix(Transformator.transponseMainDiagonal(inversedMatrix))
    }

    private fun enterConstant(): Double {
        println("Enter constant:")
        return scanner.nextLine().toDouble()
    }

    private fun enterMatrix(
        messageForSize: String = "Enter matrix size: ",
        messageForMatrix: String = "Enter matrix:"
    ): Matrix {

        println(messageForSize)
        val (rows, columns) = scanner.nextLine().split(' ').map { it.toInt() }

        println(messageForMatrix)
        val list = mutableListOf<Double>()

        repeat(rows) {
            val line = scanner.nextLine()
            list.addAll(line.split(' ').map { it.toDouble() }.toMutableList())
        }

        return Matrix(rows, columns).initWith(list)
    }

    private fun printMatrix(matrix: Matrix) {
        matrix.getMatrix().forEach { row ->
            row.forEach {
                print("$it ")
            }
            println()
        }
    }
}


