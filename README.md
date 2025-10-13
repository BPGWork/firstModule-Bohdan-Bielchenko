This program encrypts, decrypts and selects a key using the brute force method
Encryption is performed using the Caesar cipher. The program automatically determines the element alphabet
Supports Ukrainian, English alphabets and various characters
The program can be run via arguments:
1. Commands <path> <key> -> for encrypting and decrypting information
2. BRUTE_FORSE <path> [verifyPath] -> for decrypting and obtaining the key, if there is a verification file
3. BRUTE_FORSE <path> -> for decrypting and obtaining the key, using the method of verifying information by reading
4. HELP -> Command prompt and using the program

The program can be run without arguments and interact with it via the built-in interface
Attention should be paid to:
1. using Enum to store alphabets and interact with them
2. encoding system in the CaesarCipher.class class
3. error catching

Ця програма шифрує, розшифровує та підбирає ключ за методом брутфорса
Шифровка відбувається за шифром Цезаря. Програма автоматично визначає алфавіт елемента
Підтримується український, англійський алфавіт та різні знаки
Програму можна запустити через аргументи: 
1. Commands <path> <key> -> для шифровки та дешифровки інформації
2. BRUTE_FORSE <path> [verifyPath] -> для дешифровки та отримання ключа, якщо є перевіряючий файл
3. BRUTE_FORSE <path> -> для дешифровки та отримвння ключа, методом перевірки інформації читанням
4. HELP -> Підсказка команд та використання програми

Програму можна запустити без аргументів та взаємодіяти з нею через встроєний інтерфейс
Увагу потрібно приділити на:
1. використання Enum для храніння алфавітів та взаємодії з ними
2. систему кодування у класі CaesarCipher.class
3. відловку ошибок 
