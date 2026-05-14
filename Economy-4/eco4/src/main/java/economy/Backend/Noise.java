package economy.Backend;

public class Noise {
    

    public static int mapSize;

    public static int maxHeight;

    public static int variance;

    public static int[][] generate2DNoiseMap(int noiseType, int mapSize) {

        System.out.println(noiseType);

        Noise.mapSize = mapSize;

        maxHeight = 100;

        variance = 50;

        int[][] noiseMap;

        switch (noiseType) {
            case 0:
                // random noise
                noiseMap = genRandomNoise();
                break;
            case 1:
                // smooth random
                noiseMap = addStatic(smooth(genRandomNoise()), 100);
                break;
            case 2:
                noiseMap = smooth(addStatic(smooth(addStatic(smooth(genLayeredContNoise(4)), 50)), 15));
                break;
            case 3:
                noiseMap = smooth(genContNoise());
                break;
            case 4:
                noiseMap = smooth(addStatic(genContNoise(), 30));
                break;
            case 5:
                noiseMap = smooth(addStatic(smooth(addStatic(genLayeredContNoise(4), 80)), 30));
                break;
            case 6:
                noiseMap = smooth(addStatic(smooth(addStatic(genLayeredContNoise(6), 70)), 40));
                break;
            case 7:
                noiseMap = addErosion(smooth(addStatic(smooth(addStatic(gen2dTrigNoise(50, 4), 70)), 20)), 2, 10);
                break;
            case 8:
                noiseMap = addStatic(smooth(betterErosion(smooth(addStatic(smooth(gen2dTrigNoise(50, 4)), 30)), 10)), 3);
                break;
            case 9:
                noiseMap = smooth(betterErosion(smooth(addStatic(trig2DNoise(200),30)), 10));
                break;
            default:
                noiseMap = genRandomNoise();
                break;
        }

        return noiseMap;
    }

    public static int[][] betterErosion(int[][] initialMap, int layers) {

        int[][] map = initialMap;

        for (int i = 0; i < layers; i++) {
            for (int x = 0; x < mapSize; x++) {
                for (int y = 0; y < mapSize; y++) {
                    int curHeight = map[x][y];
                    if (curHeight < 85 + i && curHeight > 50 - i) {
                        int curX = x;
                        int curY = y;

                        int lowX = x;
                        int lowY = y;
                        for (int e = 0; e < 150; e++) {
                            if (map[curX][curY] < 25) {
                                e = 200;
                            }
                            if ((curX + 1) < mapSize && curHeight < map[curX+1][curY]) {
                                curHeight = map[curX+1][curY];
                                lowX = curX + 1;
                                lowY = curY;
                            }
                            if ((curY + 1) < mapSize && (curX + 1) < mapSize && curHeight < map[curX+1][curY+1]) {
                                curHeight = map[curX+1][curY+1];
                                lowX = curX + 1;
                                lowY = curY + 1;
                            }
                            if ((curY - 1) >= 0 && (curX + 1) < mapSize && curHeight < map[curX+1][curY-1]) {
                                curHeight = map[curX+1][curY-1];
                                lowX = curX + 1;
                                lowY = curY - 1;
                            }
                            if ((curY + 1) < mapSize && curHeight < map[curX][curY+1]) {
                                curHeight = map[curX][curY+1];
                                lowX = curX;
                                lowY = curY + 1;
                            }
                            if ((curY - 1) >= 0 && curHeight < map[curX][curY-1]) {
                                curHeight = map[curX][curY-1];
                                lowX = curX;
                                lowY = curY - 1;
                            }
                            if ((curX - 1) >= 0 && curHeight < map[curX-1][curY]) {
                                curHeight = map[curX-1][curY];
                                lowX = curX - 1;
                                lowY = curY;
                            }
                            if ((curY + 1) < mapSize && (curX - 1) >= 0 && curHeight < map[curX-1][curY+1]) {
                                curHeight = map[curX-1][curY+1];
                                lowX = curX - 1;
                                lowY = curY + 1;
                            }
                            if ((curY - 1) >= 0 && (curX - 1) >= 0 && curHeight < map[curX-1][curY-1]) {
                                curHeight = map[curX-1][curY-1];
                                lowX = curX - 1;
                                lowY = curY - 1;
                            }

                            int decreaseAmount = 1;
                            if (map[curX][curY] > map[lowX][lowY]) {

                                map[curX][curY] -= decreaseAmount;
                                curX = lowX;
                                curY = lowY;
                            } else {
                                e = 200;
                                map[curX][curY] -= decreaseAmount;
                                if (curY + 1 < mapSize) {
                                    map[curX][curY + 1] -= decreaseAmount;
                                }
                                
                                if (curY - 1 >= 0) {
                                    map[curX][curY - 1] -= decreaseAmount;
                                }
                                if (curX + 1 < mapSize) {
                                    if (curY + 1 < mapSize) {
                                        map[curX + 1][curY + 1] -= decreaseAmount;
                                    }
                                    map[curX + 1][curY] -= decreaseAmount;
                                    if (curY - 1 >= 0) {
                                        map[curX + 1][curY - 1] -= decreaseAmount;
                                    }
                                }

                                if (curX - 1 >= 0) {
                                    if (curY + 1 < mapSize) {
                                        map[curX - 1][curY + 1] -= decreaseAmount;
                                    }
                                    map[curX - 1][curY] -= decreaseAmount;
                                    if (curY - 1 >= 0) {
                                        map[curX - 1][curY - 1] -= decreaseAmount;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        return map;
    }

    public static int[][] trig2DNoise(int layers) {
        int[][] noiseMap = new int[mapSize][mapSize];

        
        int amplitude = maxHeight / 3;

        double frequency = 2;

        for (int i = 0; i < layers; i++) {

            double offSetX = (Math.random() * Math.PI * 2);
            double offSetY = (Math.random() * Math.PI * 2); 

            for (int x = 0; x < mapSize; x++) {
                for (int y = 0; y < mapSize; y++) {
                    
                    int xComp = (int)(amplitude * (Math.sin(x*(frequency/100) + offSetX)));
                    int yComp = (int)(amplitude * (Math.sin(y*(frequency/100) + offSetY)));
                    
                    
                    noiseMap[x][y] += xComp + yComp;
                }
            }
            amplitude = (int)(amplitude * .99);
            frequency *= 1.1;

        }
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                if (noiseMap[x][y] < 0) {
                    noiseMap[x][y] = 0;
                } else if (noiseMap[x][y] > maxHeight) {
                    noiseMap[x][y] = maxHeight;
                }

            }
        }

        return noiseMap;
    }

    public static int[][] genRandomNoise() {

        int[][] noiseMap = new int[mapSize][mapSize];

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {

                int ranInt = (int)(Math.random() * maxHeight);

                noiseMap[x][y] = ranInt;

            }
        }

        return noiseMap;
    }

    public static int[][] genRandomNoise(int range, int center) {

        int[][] noiseMap = new int[mapSize][mapSize];

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {

                int ranInt = (int)(Math.random() * range) - (range / 2);

                noiseMap[x][y] = ranInt;

            }
        }

        return noiseMap;
    }

    public static int[][] genContNoise() {



        int[][] noiseMap = new int[mapSize][mapSize];


        int[] xArr = gen1DContNoise(50, variance);
        int[] yArr = gen1DContNoise(50, variance);

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                noiseMap[x][y] = xArr[x] + yArr[y];
            }
       
        }

        

        return noiseMap;

    }

    public static int[][] genLayeredContNoise(int layers) {

        int curVar = (int) variance;
        int curAmp = (int) maxHeight / 2;

        int[][] noiseMap = new int[mapSize][mapSize];

        for (int i = 0; i < layers; i++) {

            int[] xArr = gen1DContNoise(curAmp, 0, curVar);
            int[] yArr = gen1DContNoise(curAmp, 0, curVar);

            for (int y = 0; y < mapSize; y++) {
                for (int x = 0; x < mapSize; x++) {
                    noiseMap[x][y] += xArr[x] + yArr[y];
                    if (noiseMap[x][y] < 0) {
                        noiseMap[x][y] = 0;
                    }
                    if (noiseMap[x][y] > maxHeight) {
                        noiseMap[x][y] = maxHeight;
                    }
                }
       
            }

            curAmp /= 2;
            curVar = (int) (curVar * .75);
        }
    
        return noiseMap;

    }

    public static int[] gen1DContNoise(int amplitude, int variance) {

        int cur = (int)(Math.random() * amplitude);
        int[] vals = new int[mapSize];


        for (int i = 0; i < mapSize; i++) {


            int change = (int)((Math.random() * (variance)) - (variance / 2));

            cur += change;

            if (cur < 0) {
                cur = 0;
            }
            if (cur > amplitude) {
                cur = amplitude;
            }
            vals[i] = cur;

            //System.out.println(cur);
        }

        return vals;
    }

    public static int[] gen1DContNoise(int amplitude, int center, int variance) {

        int cur = (int)(Math.random() * amplitude);
        int[] vals = new int[mapSize];

        System.out.println("START: var" + variance + ", amp " + amplitude);

        for (int i = 0; i < mapSize; i++) {


            int change = (int)((Math.random() * (variance)) - (variance / 2));

            cur += change;

            if (cur < (center - amplitude)) {
                cur = center - amplitude;
            }
            if (cur > (center + amplitude)) {
                cur = (center + amplitude);
            }
            vals[i] = cur;
            System.out.println(cur);
        }

        return vals;
    }

    public static int[] gen1DTrigNoise(int amplitude, int layers) {

        //int resolution = 100;

        double stepSize = (1 * Math.PI) / mapSize;

        int[] vals = new int[mapSize];

        int amp = (int) amplitude / 2;
        double ampChange = .75;

        int frequency = 2;

        for (int i = 0; i < layers; i++) {

            double randomOffset = (int)(Math.random() * mapSize * 2);

            double curX = randomOffset * stepSize;



            for (int r = 0; r < mapSize; r++) {
                int curVal = (int)(amp * Math.cos(frequency * (curX + (r * stepSize))) + amp);

                vals[r] += curVal;
                //System.out.println(curVal);
                if (vals[r] > maxHeight) {
                    vals[r] = maxHeight;
                }
            }
            
            frequency *= (int) 2;
            amp = (int)(amp * ampChange);
        }

        return vals;
    }

    public static int[][] gen2dTrigNoise(int amplitude, int layers) {



        int [][] trigNoiseMap = new int[mapSize][mapSize];

        int[] xArr = gen1DTrigNoise(amplitude, layers);
        int[] yArr = gen1DTrigNoise(amplitude, layers);

        for (int y = 0; y < mapSize; y++) {
            
            for (int x = 0; x < mapSize; x++) {

                trigNoiseMap[x][y] = (int)(xArr[x] + yArr[y]) / 2;
            }
        }

        return trigNoiseMap;
    }

    public static int[][] smooth(int[][] originalNoiseMap) {
        int[][] noiseMap = originalNoiseMap;

        for (int y = 0; y < noiseMap.length; y++) {

            for (int x = 0; x < noiseMap.length; x++) {

                int curVal = noiseMap[x][y];
                int numIncluded = 1;

                if (x > 0) {
                    curVal += noiseMap[x - 1][y];
                    numIncluded++;
                    if (y > 0) {
                        curVal += noiseMap[x - 1][y - 1];
                        curVal += noiseMap[x][y - 1];
                        numIncluded += 2;
                    }
                    if (y < noiseMap.length - 1) {
                        curVal += noiseMap[x - 1][y + 1];
                        curVal += noiseMap[x][y + 1];
                        numIncluded += 2;
                    }
                }
                if (x < noiseMap.length - 1) {
                    curVal += noiseMap[x + 1][y];
                    numIncluded++;
                    if (y > 0) {
                        curVal += noiseMap[x + 1][y - 1];
                        numIncluded++;
                    }
                    if (y < noiseMap.length - 1) {
                        curVal += noiseMap[x + 1][y + 1];
                        numIncluded++;
                    }
                }

                curVal = (int)(curVal / numIncluded);

                noiseMap[x][y] = curVal;
                
            }
        }

        return noiseMap;
    }

    public static int[][] addStatic(int[][] originalArr, int intensity) {
        int[][] nuArr = originalArr;

        int[][] noise = genRandomNoise(intensity, 0);

        for (int y = 0; y < nuArr.length; y++) {

            for (int x = 0; x < nuArr.length; x++) {
                nuArr[x][y] += noise[x][y];

                if (nuArr[x][y] < 0) {
                    nuArr[x][y] = 0;
                }
                if (nuArr[x][y] > maxHeight) {
                    nuArr[x][y] = maxHeight;
                }
                
                //System.out.println(nuArr[x][y]);
            }
        }

        return nuArr;
    }


    public static int[][] addErosion(int[][] map, int steps, int erosionConst) {


        for (int i = 0; i < steps; i++) {
            int ranX = (int)(Math.random() * mapSize);
            int ranY = (int)(Math.random() * mapSize);

            int curX = ranX;
            int curY = ranY;

            boolean isLower = true;
            while (isLower) {
                map[curX][curY] -= erosionConst;
                if (map[curX][curY] < 0) {
                    map[curX][curY] = 0;
                }
                int lowest = map[curX][curY];
                int lowX = curX;
                int lowY = curY;
                //System.out.println("X: " + curX + ", Y: " + curY);


                if (curX + 1 < mapSize) {

                    if ((curX + 1) < mapSize && map[curX + 1][curY] > lowest) {
                        lowest = map[curX + 1][curY];
                        lowX = curX + 1;
                        lowY = curY;
                    }
                    if ((curY + 1) < mapSize && map[curX + 1][curY + 1] > lowest) {
                        lowest = map[curX + 1][curY + 1];
                        lowX = curX + 1;
                        lowY = curY + 1;
                    }
                    
                    if ((curY - 1) > 0 && map[curX + 1][curY - 1] > lowest) {
                        lowest = map[curX][curY];
                        lowX = curX;
                        lowY = curY;
                    }
                }
                if ((curY - 1) > 0 && map[curX][curY - 1] > lowest) {
                    lowest = map[curX][curY - 1];
                    lowX = curX;
                    lowY = curY - 1;
                }

                if (curX - 1 > 0) {
                    if (map[curX - 1][curY] > lowest) {
                        lowest = map[curX - 1][curY];
                        lowX = curX - 1;
                        lowY = curY;
                    }
                    if ((curY - 1) > 0 && map[curX - 1][curY - 1] > lowest) {
                        lowest = map[curX - 1][curY - 1];
                        lowX = curX - 1;
                        lowY = curY - 1;
                    }
                    if ((curY + 1) < mapSize && map[curX - 1][curY + 1] > lowest) {
                        lowest = map[curX - 1][curY + 1];
                        lowX = curX - 1;
                        lowY = curY + 1;
                    }
                }
                if ((curY + 1) < mapSize && map[curX][curY + 1] > lowest) {
                    lowest = map[curX][curY + 1];
                    lowX = curX;
                    lowY = curY + 1;
                }

                if (lowest == map[curX][curY]) {
                    isLower = false;
                }

                curX = lowX;
                curY = lowY;

            }


        }

        return map;
    }
    

}


