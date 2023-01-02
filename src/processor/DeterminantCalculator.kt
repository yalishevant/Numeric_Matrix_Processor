package processor

object DeterminantCalculator {
    private fun getForOneDimMatrix(matrix: Matrix): Double {
        return matrix.getMatrix()[0][0]
    }

    private fun getForTwoDimMatrix(matrix: Matrix): Double {
        val a00 = matrix.getMatrix()[0][0]
        val a01 = matrix.getMatrix()[0][1]
        val a10 = matrix.getMatrix()[1][0]
        val a11 = matrix.getMatrix()[1][1]

        return a00*a11 - a01*a10
    }

    fun getForNDimMatrix(matrix: Matrix): Double {

        val dim = matrix.getMatrix().size

        if (dim == 1)
            return getForOneDimMatrix(matrix)

        if (dim == 2)
            return getForTwoDimMatrix(matrix)

        var result = 0.0

        for (i in 0 until  matrix.columns) {
            val minor = matrix.getMinor(0, i)
            val sign: Int = if (i % 2 == 0) 1 else -1
            result += (sign * matrix.getMatrix()[0][i] * getForNDimMatrix(minor))
        }

        return result
    }
}