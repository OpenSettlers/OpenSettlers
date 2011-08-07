package org.soc.common.server.randomization;

/*
 * Wrapper interface for retrieval of random numbers. Abstracted to 
 * support web services providing dice rolls, hardware random generators etc
 */
public interface Random
{
    public int nextInt(int max, boolean base1);
}
