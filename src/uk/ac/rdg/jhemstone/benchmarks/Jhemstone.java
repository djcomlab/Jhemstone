 /**
 * Jhemstone suite of computational benchmarks for Java ME
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

import java.util.Date;
import net.sf.microlog.core.Logger;
import net.sf.microlog.core.LoggerFactory;

/**
 *
 * @author David Johnson
 */
public class Jhemstone {
    
    private int num_runs = 0;
    private Logger log = LoggerFactory.getLogger(Jhemstone.class);
    
    public void run_benchmarks(int num_runs) {
        this.num_runs = num_runs;
        log.info("Running Jhemstone for " + num_runs + " iterations");
        log.info("Benchmarking begins " + new Date(System.currentTimeMillis()).toString());
        run_linpack();
        run_sieve();
        log.info("Benchmarking ends " + new Date(System.currentTimeMillis()).toString());
    }
    
    public void run_linpack() {
        double cumulative_mflops = 0;
        double cumulative_residn = 0;
        double cumulative_time = 0;
        double cumulative_eps = 0;
        double cumulative_total = 0; 
        int psize = 250;
        Linpack linpack = null;
        log.info("Starting LINPACK runs with problem size " + psize);
        for(int i=0;i<num_runs;i++) {
            linpack = new Linpack();
            System.gc();
            linpack.run_benchmark(psize,psize*2);
            log.info("run=" + (i+1) + "; mflops=" + linpack.get_mflops_result() + "; residn=" + linpack.get_residn_result() + "; time=" + linpack.get_time_result() + "; ep =" + linpack.get_eps_result() + "; total=" + linpack.get_total());
            cumulative_mflops = cumulative_mflops + linpack.get_mflops_result();
            cumulative_residn = cumulative_residn + linpack.get_residn_result();
            cumulative_time = cumulative_time + linpack.get_time_result();
            cumulative_eps = cumulative_eps + linpack.get_eps_result();
            cumulative_total = cumulative_total + linpack.get_total();                
        }  
        // print average of results
        log.info("Completed " + num_runs + " runs of LINPACK benchmark in " + cumulative_time + " seconds");
        log.info("Average mflops=" + (cumulative_mflops/num_runs));
        log.info("Average residn=" + (cumulative_residn/num_runs));
        log.info("Average time=" + (cumulative_time/num_runs));
        log.info("Average eps=" + (cumulative_eps/num_runs));
        log.info("Average total=" + (cumulative_total/num_runs));
    }
    
    public void run_sieve() {
        int cumulative_iterations = 0;
        double cumulative_seconds = 0;
        double cumulative_score = 0; 
        int runtime = 10000; // 10 seconds
        Sieve sieve = null;
        log.info("Starting Sieve runs with run time " + runtime + " milliseconds");
        for(int i=0;i<num_runs;i++) {
            sieve = new Sieve();
            System.gc();
            sieve.run_benchmark(runtime);
            log.info("Completed " + sieve.get_iterations() + " iterations in " + sieve.get_seconds() + "; sieve score = " + sieve.get_score());
            cumulative_iterations = cumulative_iterations + sieve.get_iterations();
            cumulative_seconds = cumulative_seconds +  sieve.get_seconds();
            cumulative_score = cumulative_score + sieve.get_score();
        }
        // print average of results
        log.info("Completed " + num_runs + " runs of Sieve of Eratosthenes benchmark in " + cumulative_seconds + " seconds");
        log.info("Average iterations = " + (cumulative_iterations/num_runs));
        log.info("Average seconds = " + (cumulative_seconds/num_runs));
        log.info("Average score = " + (cumulative_score/num_runs));
    }
}
