package processor

import kotlin.math.pow

/**
* Makes moore complex operations with matrix: transpositions and inversion
*/
object Transformator {
    fun inverse(matrix: Matrix): Matrix? {
        val determinant = DeterminantCalculator.getForNDimMatrix(matrix)
        if (determinant == 0.0) return null

        val adjointMatrix: Matrix = getAdjoint(matrix)

        val constant = (1 / determinant)
        return adjointMatrix.multiplyByConst(constant)
    }

    private fun getAdjoint(matrix: Matrix): Matrix {

        val list: MutableList<Double> = mutableListOf()
        for (i in 0 until matrix.rows) {
            for (j in 0 until matrix.columns) {
                val sign = (-1).toDouble().pow(i + j)
                val minor: Matrix = matrix.getMinor(i, j)
                val det = DeterminantCalculator.getForNDimMatrix(minor)
                list.add(sign * det)
            }
        }

        return Matrix(matrix.rows, matrix.columns).initWith(list)
    }



    fun transponseMainDiagonal(matrix: Matrix): Matrix {
        val result = Matrix(matrix.columns, matrix.rows)

        for (row in 0 until matrix.rows) {
            for (column in 0 until matrix.columns) {
                result.getMatrix()[column][row] = matrix.getMatrix()[row][column]
            }
        }
        return result
    }

    fun transponseSideDiagonal(matrix: Matrix): Matrix {
        val result = Matrix(matrix.columns, matrix.rows)

        for (row in 0 until matrix.rows) {
            for (column in 0 until matrix.columns) {
                result.getMatrix()[column][row] = matrix.getMatrix()[matrix.rows-row-1][matrix.columns-column-1]
            }
        }
        return result
    }

    fun transponseVertical(matrix: Matrix): Matrix {
        val result = Matrix(matrix.rows, matrix.columns)

        for (row in 0 until matrix.rows) {
            for (column in 0 until matrix.columns) {
                result.getMatrix()[row][column] = matrix.getMatrix()[row][matrix.columns-column-1]
            }
        }
        return result
    }

    fun transponseHorizontal(matrix: Matrix): Matrix {
        val result = Matrix(matrix.rows, matrix.columns)

        for (row in 0 until matrix.rows) {
            for (column in 0 until matrix.columns) {
                result.getMatrix()[row][column] = matrix.getMatrix()[matrix.rows-row-1][column]
            }
        }
        return result
    }
}