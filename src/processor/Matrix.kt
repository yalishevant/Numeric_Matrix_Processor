package processor

class Matrix(val rows: Int, val columns: Int) {

    private val matrix: MutableList<MutableList<Double>> = MutableList(rows) {
            MutableList(columns) {
                0.0
            }
        }

    fun initWith(list: MutableList<Double>): Matrix {
        for (row in 0 until this.rows) {
            for (column in 0 until this.columns) {
                this.matrix[row][column] = list.removeFirst()
            }
        }

        return this
    }

    fun getMatrix() = matrix

    fun add(m: Matrix): Matrix? {
        if (this.rows != m.rows || this.columns != m.columns) {
            println("The operation cannot be performed.")
            return null
        }

        val result = Matrix(this.rows, this.columns)

        for (row in 0 until rows) {
            for (column in 0 until columns) {
                result.matrix[row][column] = this.matrix[row][column] + m.matrix[row][column]
            }
        }
        return result
    }

    fun multiplyByConst(c: Double): Matrix {

        val result = Matrix(this.rows, this.columns)

        for (row in 0 until rows) {
            for (column in 0 until columns) {
                result.matrix[row][column] = String.format("%.2f", this.matrix[row][column] * c).toDouble()
            }
        }
        return result
    }

    fun multiply(matrixB: Matrix): Matrix? {
        if (this.columns != matrixB.rows) {
            println("The operation cannot be performed.")
            return null
        }

        // 00*00 + 01*10 + 02*20; 00*01 + 01*11 + 02*21; 00*02 + 01*12 + 02*22
        // 10*00 + 11*10 + 12*20; 10*01 + 11*11 + 12*21; 10*02 + 11*12 + 12*22
        val list = mutableListOf<Double>()
        for (a in 0 until this.rows) {
            for (i in 0 until matrixB.columns) {
                var acc = 0.0
                for (j in 0 until this.columns) {
                    acc += this.matrix[a][j] * matrixB.matrix[j][i]
                }
                list.add(acc)
            }
        }
        return Matrix(rows, matrixB.columns).initWith(list)
    }

    fun getMinor(row: Int, column: Int): Matrix {
        val minor: MutableList<MutableList<Double>> = matrix.map { it.toMutableList() }.toMutableList()
        minor.removeAt(row)
        for (minorRow in 0 until minor.size) {
            minor[minorRow].removeAt(column)
        }
        return Matrix(minor.size, minor[0].size).initWith(minor.flatten().toMutableList())
    }
}