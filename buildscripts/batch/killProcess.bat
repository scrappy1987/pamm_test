FOR /F "tokens=5 delims= " %%P IN ('netstat -a -n -o ^| findstr 0.0.0.0:%1') DO TaskKill.exe /PID %%P /F
