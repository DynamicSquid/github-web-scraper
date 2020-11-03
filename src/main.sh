tput reset

printf "username: "
read username

python main.py $username
if (( $? == 0 )); then
  javac main.java; java Main $username
  rm Main.class
fi
