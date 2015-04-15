Øvingen har frist fredag 17. april klokken 18.00 
Øvingen må demonstreres på sal før denne tiden. 
Test programmet med forskjellige verdier. 
Vær forberedt på spørsmål om hva som skjer når man endrer verdiene. 

Tips P3: 
-The simulate method in the simulator class is a good starting point. 

-Only the simulator class should add events to the event queue. 

-Whenever a process is put into either CPU or IO, you probably want 
to create an event to be put into the event queue. 

-There are similar functionality for the different event types. 
The similar functionality should be in a method so it can be reuse for the 
different event types. Think object oriented programming.

-There will always be an event of type NEW_PROCESS in the event queue. Why?

-Run the simula%on with different parameters. Create a table or sheet with the results 

-Complete the partial solution. This involves completing the classes Process, Simulator, Cpu and Io

-The Cpu and Io classes will resemble the Memory class, but are a bit more complicated
