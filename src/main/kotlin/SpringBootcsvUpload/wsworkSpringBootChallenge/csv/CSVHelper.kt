package SpringBootcsvUpload.wsworkSpringBootChallenge.csv

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie
import org.apache.commons.csv.*
import org.springframework.web.multipart.MultipartFile
import java.io.*

class CSVHelper {



    //hasCSVFormat usado para verificar se o formato do arquivo é CSV ou não.
    companion object{
        var HEADERs = arrayOf("Id", "MarcaId", "MarcaNome", "Modelo", "Ano", "Combustivel", "NumPortas", "ValorFipe", "Cor")
        var TYPE = "text/csv"
        fun hasCSVFormat(file: MultipartFile): Boolean {
            return TYPE == file.contentType || file.contentType == "application/vnd.ms-excel"
        }

        fun csvToCar(`is`: InputStream): List<Car> {
            try {
                BufferedReader(InputStreamReader(`is`, "UTF-8")).use { fileReader ->
                    CSVParser(fileReader,
                            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()).use { csvParser ->
                        val carList = mutableListOf<Car>()
                        val csvRecords: Iterable<CSVRecord> = csvParser.getRecords()
                        for (csvRecord in csvRecords) {
                            val factorie = Factorie(csvRecord.get("MarcaNome"), null)
                            factorie.id = csvRecord.get("MarcaId").toInt()

                            val car = Car(csvRecord.get("Modelo"),
                                    csvRecord.get("Ano"),
                                    csvRecord.get("Combustivel"),
                                    csvRecord.get("NumPortas"),
                                    csvRecord.get("ValorFipe"),
                                    csvRecord.get("Cor"),
                                    factorie
                            )
                            car.id = csvRecord.get("Id").toInt()
                            carList.add(car)
                        }
                        return carList.toList()
                    }
                }
            } catch (e: IOException) {
                throw RuntimeException("fail to parse CSV file: " + e.message)
            }
        }
    }
}




