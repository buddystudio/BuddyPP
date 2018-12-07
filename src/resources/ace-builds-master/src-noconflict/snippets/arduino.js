ace.define("ace/snippets/arduino",[], function(require, exports, module) {
"use strict";

exports.snippetText = "## Arduino Collections\n\
snippet void setup\n\
	void setup()\n\
	{\n\
		\n\
	}\n\
snippet void loop\n\
	void loop()\n\
	{\n\
		\n\
	}\n\
snippet digitalWrite\n\
	digitalWrite(${1:pin}, ${2:value});\n\
snippet digitalRead\n\
	digitalRead(${1:pin});\n\
snippet analogWrite\n\
	analogWrite(${1:pin}, ${2:value});\n\
snippet analogRead\n\
	analogRead(${1:pin});\n\
snippet analogReference\n\
	/* type: DEFAULT, INTERNAL, INTERNAL1V1, INTERNAL2V56, or EXTERNAL . */\n\
	analogReference(${1:type});\n\
snippet analogReadResolution()\n\
	analogReadResolution(${1:bits});\n\
snippet analogWriteResolution()\n\
	analogWriteResolution(${1:bits});\n\
snippet tone\n\
	tone(${1:pin}, ${2:frequency}, ${3:duration});\n\
snippet tone\n\
	tone(${1:pin}, ${2:frequency});\n\
snippet noTone\n\
	noTone(${1:pin});\n\
snippet shiftOut\n\
	shiftOut(${1:dataPin}, ${2:clockPin}, ${3:bitOrder}, ${4:value});\n\
snippet shiftIn\n\
	shiftIn(${1:dataPin}, ${2:clockPin}, ${3:bitOrder});\n\
snippet pulseIn\n\
	pulseIn(${1:pin}, ${2:value});\n\
snippet pulseIn\n\
	pulseIn(${1:pin}, ${2:value}, ${3:timeout});\n\
snippet millis\n\
	millis();\n\
snippet micros\n\
	micros();\n\
snippet delay\n\
	delay(${1:ms});\n\
snippet delayMicroseconds\n\
	delayMicroseconds(${1:us});\n\
snippet min\n\
	min(${1:x}, ${2:y});\n\
snippet max\n\
	max(${1:x}, ${2:y});\n\
snippet abs\n\
	abs(${1:x});\n\
snippet constrain\n\
	constrain(${1:x}, ${2:a}, ${3:b});\n\
snippet map\n\
	map(${1:value}, ${2:fromLow}, ${3:fromHigh}, ${4:toLow}, ${5:toHigh});\n\
snippet pow\n\
	pow(${1:base}, ${2:exponent});\n\
snippet sqrt\n\
	sqrt(${1:x});\n\
snippet sin\n\
	sin(${1:rad});\n\
snippet cos\n\
	cos(${1:rad});\n\
snippet tan\n\
	tan(${1:rad});\n\
snippet isAlphaNumeric\n\
	isAlphaNumeric(${1:thisChar});\n\
snippet isAlpha\n\
	isAlpha(${1:thisChar});\n\
snippet isAscii\n\
	isAscii(${1:thisChar});\n\
snippet isWhitespace\n\
	isWhitespace(${1:thisChar});\n\
snippet isControl\n\
	isControl(${1:thisChar});\n\
snippet isDigit\n\
	isDigit(${1:thisChar});\n\
snippet isGraph\n\
	isGraph(${1:thisChar});\n\
snippet isLowerCase\n\
	isLowerCase(${1:thisChar});\n\
snippet isPrintable\n\
	isPrintable(${1:thisChar});\n\
snippet isPunct\n\
	isPunct(${1:thisChar});\n\
snippet isSpace\n\
	isSpace(${1:thisChar});\n\
snippet isUpperCase\n\
	isUpperCase(${1:thisChar});\n\
snippet isHexadecimalDigit\n\
	isHexadecimalDigit(${1:thisChar});\n\
snippet randomSeed\n\
	randomSeed(${1:seed});\n\
snippet random\n\
	random(${1:min}, ${2:max});\n\
snippet random\n\
	random(${1:max});\n\
snippet lowByte\n\
	lowByte(${1:x});\n\
snippet highByte\n\
	highByte(${1:x});\n\
snippet bitRead\n\
	bitRead(${1:x}, ${2:n});\n\
snippet bitWrite\n\
	bitWrite(${1:x}, ${2:n}, ${3:b});\n\
snippet bitSet\n\
	bitSet(${1:x}, ${2:n});\n\
snippet bitClear\n\
	bitClear(${1:x}, ${2:n});\n\
snippet bit\n\
	bit(${1:n});\n\
snippet attachInterrupt\n\
	attachInterrupt(digitalPinToInterrupt(${1:pin}), ${2:ISR}, ${3:mode});\n\
snippet attachInterrupt\n\
	attachInterrupt(${1:interrupt}, ${2:ISR}, ${3:mode});\n\
snippet attachInterrupt\n\
	attachInterrupt(${1:pin}, ${2:ISR}, ${3:mode});\n\
snippet detachInterrupt\n\
	detachInterrupt(${1:interrupt});\n\
snippet detachInterrupt\n\
	detachInterrupt(${1:pin});\n\
snippet detachInterrupt\n\
	detachInterrupt(digitalPinToInterrupt(${1:pin}));\n\
snippet interrupts\n\
	interrupts();\n\
snippet noInterrupts\n\
	noInterruptss();\n\
snippet Serial.available\n\
	Serial.available();\n\
snippet Serial.availableForWrite\n\
	Serial.availableForWrite()\n\
snippet Serial.begin\n\
	Serial.begin(${1:speed}, ${2:config});\n\
snippet Serial.begin\n\
	Serial.begin(${1:speed});\n\
snippet Serial.end\n\
	Serial.end();\n\
snippet Serial.find\n\
	Serial.find(${1:target});\n\
snippet Serial.findUntil\n\
	Serial.findUntil(${1:target}, ${2:terminal});\n\
snippet Serial.flush\n\
	Serial.flush();\n\
snippet Serial.parseFloat\n\
	Serial.parseFloat();\n\
snippet Serial.parseInt\n\
	Serial.parseInt();\n\
snippet Serial.peek\n\
	Serial.peek();\n\
snippet Serial.print\n\
	Serial.print(${1:val}, ${2:format});\n\
snippet Serial.print\n\
	Serial.print(${1:val});\n\
snippet Serial.println\n\
	Serial.println(${1:val}, ${2:format});\n\
snippet Serial.println\n\
	Serial.println(${1:val});\n\
snippet Serial.read\n\
	Serial.read();\n\
snippet Serial.readBytes\n\
	Serial.readBytes(${1:buffer}, ${2:length});\n\
snippet Serial.readBytesUntil\n\
	Serial.readBytesUntil(${1:character}, ${2:buffer}, ${3:length});\n\
snippet Serial.readString\n\
	Serial.readString();\n\
snippet Serial.readStringUntil\n\
	Serial.readStringUntil(${1:terminator});\n\
snippet Serial.setTimeout\n\
	Serial.setTimeout(${1:time});\n\
snippet Serial.write\n\
	Serial.write(${1:val});\n\
snippet Serial.write\n\
	Serial.write(${1:str});\n\
snippet Serial.write\n\
	Serial.write(${1:buf}, ${2:len});\n\
snippet Serial.serialEvent\n\
	Serial.serialEvent()\n\
	{\n\
		\n\
	}\n\
snippet pinMode\n\
	pinMode(${1:PIN}, ${2:MODE});\n\
snippet Keyboard.begin\n\
	Keyboard.begin();\n\
snippet Keyboard.end\n\
	Keyboard.end();\n\
snippet Keyboard.press\n\
	Keyboard.press();\n\
snippet Keyboard.print\n\
	Keyboard.print();\n\
snippet Keyboard.println\n\
	Keyboard.println();\n\
snippet Keyboard.release\n\
	Keyboard.release();\n\
snippet Keyboard.releaseAll\n\
	Keyboard.releaseAll();\n\
snippet Keyboard.write\n\
	Keyboard.write();\n\
snippet Mouse.begin\n\
	Mouse.begin();\n\
snippet Mouse.click\n\
	Mouse.click();\n\
snippet Mouse.end\n\
	Mouse.end();\n\
snippet Mouse.move\n\
	Mouse.move();\n\
snippet Mouse.press\n\
	Mouse.press();\n\
snippet Mouse.release\n\
	Mouse.release();\n\
snippet Mouse.isPressed\n\
	Mouse.isPressed();\n\
## STL Collections\n\
# std::array\n\
snippet array\n\
	std::array<${1:T}, ${2:N}> ${3};${4}\n\
# std::vector\n\
snippet vector\n\
	std::vector<${1:T}> ${2};${3}\n\
# std::deque\n\
snippet deque\n\
	std::deque<${1:T}> ${2};${3}\n\
# std::forward_list\n\
snippet flist\n\
	std::forward_list<${1:T}> ${2};${3}\n\
# std::list\n\
snippet list\n\
	std::list<${1:T}> ${2};${3}\n\
# std::set\n\
snippet set\n\
	std::set<${1:T}> ${2};${3}\n\
# std::map\n\
snippet map\n\
	std::map<${1:Key}, ${2:T}> ${3};${4}\n\
# std::multiset\n\
snippet mset\n\
	std::multiset<${1:T}> ${2};${3}\n\
# std::multimap\n\
snippet mmap\n\
	std::multimap<${1:Key}, ${2:T}> ${3};${4}\n\
# std::unordered_set\n\
snippet uset\n\
	std::unordered_set<${1:T}> ${2};${3}\n\
# std::unordered_map\n\
snippet umap\n\
	std::unordered_map<${1:Key}, ${2:T}> ${3};${4}\n\
# std::unordered_multiset\n\
snippet umset\n\
	std::unordered_multiset<${1:T}> ${2};${3}\n\
# std::unordered_multimap\n\
snippet ummap\n\
	std::unordered_multimap<${1:Key}, ${2:T}> ${3};${4}\n\
# std::stack\n\
snippet stack\n\
	std::stack<${1:T}> ${2};${3}\n\
# std::queue\n\
snippet queue\n\
	std::queue<${1:T}> ${2};${3}\n\
# std::priority_queue\n\
snippet pqueue\n\
	std::priority_queue<${1:T}> ${2};${3}\n\
##\n\
## Access Modifiers\n\
# private\n\
snippet pri\n\
	private\n\
# protected\n\
snippet pro\n\
	protected\n\
# public\n\
snippet pub\n\
	public\n\
# friend\n\
snippet fr\n\
	friend\n\
# mutable\n\
snippet mu\n\
	mutable\n\
## \n\
## Class\n\
# class\n\
snippet cl\n\
	class ${1:`Filename('$1', 'name')`} \n\
	{\n\
	public:\n\
		$1(${2});\n\
		~$1();\n\
\n\
	private:\n\
		${3:/* data */}\n\
	};\n\
# member function implementation\n\
snippet mfun\n\
	${4:void} ${1:`Filename('$1', 'ClassName')`}::${2:memberFunction}(${3}) {\n\
		${5:/* code */}\n\
	}\n\
# namespace\n\
snippet ns\n\
	namespace ${1:`Filename('', 'my')`} {\n\
		${2}\n\
	} /* namespace $1 */\n\
##\n\
## Input/Output\n\
# std::cout\n\
snippet cout\n\
	std::cout << ${1} << std::endl;${2}\n\
# std::cin\n\
snippet cin\n\
	std::cin >> ${1};${2}\n\
##\n\
## Iteration\n\
# for i \n\
snippet fori\n\
	for (int ${2:i} = 0; $2 < ${1:count}; $2${3:++}) {\n\
		${4:/* code */}\n\
	}${5}\n\
\n\
# foreach\n\
snippet fore\n\
	for (${1:auto} ${2:i} : ${3:container}) {\n\
		${4:/* code */}\n\
	}${5}\n\
# iterator\n\
snippet iter\n\
	for (${1:std::vector}<${2:type}>::${3:const_iterator} ${4:i} = ${5:container}.begin(); $4 != $5.end(); ++$4) {\n\
		${6}\n\
	}${7}\n\
\n\
# auto iterator\n\
snippet itera\n\
	for (auto ${1:i} = $1.begin(); $1 != $1.end(); ++$1) {\n\
		${2:std::cout << *$1 << std::endl;}\n\
	}${3}\n\
##\n\
## Lambdas\n\
# lamda (one line)\n\
snippet ld\n\
	[${1}](${2}){${3:/* code */}}${4}\n\
# lambda (multi-line)\n\
snippet lld\n\
	[${1}](${2}){\n\
		${3:/* code */}\n\
	}${4}\n\
";
exports.scope = "arduino";

});
                (function() {
                    ace.require(["ace/snippets/arduino"], function(m) {
                        if (typeof module == "object" && typeof exports == "object" && module) {
                            module.exports = m;
                        }
                    });
                })();
            