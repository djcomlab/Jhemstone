 /**
 * Eratosthenes Sieve prime number benchmark - Adapted from source from:
 *                    rsb.info.nih.gov/nih-image/java/benchmarks/sieve.html
 *
 * Copyright (C) 2008 David Johnson (d.johnson@reading.ac.uk)
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */

package uk.ac.rdg.jhemstone.benchmarks;

/**
 *
 * @author David Johnson
 */
public class Sieve {
        
    private double score = 0;
    private double seconds = 0;
    private int iterations = 0;
    
    public double get_score() {
        return score;
    }
    
    public double get_seconds() {
        return seconds;
    }
    
    public int get_iterations() {
        return iterations;
    }
    
    public void run_benchmark(int duration) {
        int SIZE = 8190;
        boolean flags[] = new boolean[SIZE + 1];
        int i, prime, k, iter, count;
        long startTime, elapsedTime;

        startTime = System.currentTimeMillis();
        while (true) {
            count = 0;
            for (i = 0; i <= SIZE; i++) {
                flags[i] = true;
            }
            for (i = 0; i <= SIZE; i++) {
                if (flags[i]) {
                    prime = i + i + 3;
                    for (k = i + prime; k <= SIZE; k += prime) {
                        flags[k] = false;
                    }
                    count++;
                }
            }
            iterations++;
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= duration) {
                break;
            }
        }
        seconds = elapsedTime / 1000.0;
        score = (double) (iterations / seconds);
    }

}
