
class GoldenCrown:

    def __init__(self, receiver, message):
        self.receiver = receiver
        self.message = message

    def check_receiver(self, receiver_kingdom, kingdom_list):
        result = ""
        for kingdom_name in kingdom_list:
            if kingdom_name.lower() == receiver_kingdom.lower():
                result = kingdom_name
            else:
                continue
        return result

    def check_message(self, receiver_kingdom: str, message_body, kingdom_list, kingdom_emblem):
        count = 0
        if receiver_kingdom.lower() == self.check_receiver(receiver_kingdom, kingdom_list):
            selected_emblem = kingdom_emblem.get(receiver_kingdom.lower())
            for i in selected_emblem:
                if selected_emblem.count(i) <= message_body.count(i):
                    count += 1
                    continue
                else:
                    break
            if len(selected_emblem) == count:
                return receiver_kingdom
            else:
                return None


count: int = 0
allies_count = 0
allies = []
kingdom_list = ("water", "fire", "land", "air", "space", "ice")
kingdom_emblem = {"air": "owl", "fire": "dragon", "ice": "mammoth", "water": "octopus", "land": "panda"}
print("who is the ruler of Southeros?")
print("None")
print("Allies of Ruler?")
print("None")
while True:
    message_input = input("Input:")
    if message_input == "":
        quit()
    result = message_input.split(",")
    g = GoldenCrown(result[0], result[1])
    alliance_kingdom = g.check_message(g.receiver, g.message, kingdom_list, kingdom_emblem)
    if alliance_kingdom:
        if alliance_kingdom not in allies:
            allies_count += 1
            allies.append(alliance_kingdom)
    count += 1
    if count == 5:
        break
    else:
        continue

print("who is the ruler of Southeros?")
if allies_count >= 3:
    print("king Shan")
else:
    print(None)
print("Allies of Ruler?")
for x in allies:
    print(x, end=" ")

