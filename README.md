# Nearby cities

Finds nearby cities for all US cities listed in the `src/main/resources/cities.csv` file. The file contains the coordinates for each city.

### Solution attempts to find nearby cities of a single city:
  1. I implemented the naive solution of calculating the distance from each other city and selecting the smallest ones. This seemed too slow, it took ~2.5 minutes on my computer to find nearby cities of each city.
  3. I added the heuristics of only searching nearby cities in the same state or in neighboring states. This reduced the running time to ~1 minute. A search index is clearly needed.
  4. I added a spatial index to the search, I used Google's S2 library. This reduced the running time to only ~1-2 seconds.

### Running the program
Run the program by emitting `sbt run` in the root directory of the project. The output will also be placed in the project root and named `output.csv`. 
The output file will contain a row for each city in the input file. The first column of the row is the source city, further columns list nearby cities.

### Things that could be improved:
  1. Even though the components are highly testable, I didn't have time to do extensive testing yet.
  2. I didn't implement reading parameters from the console or a config file, so the input/output file paths and the numbers of nearby cities to be found for each city is hardcoded in the source.
  3. Side effects could be expressed using an effect library (e.g. Cats Effect).
