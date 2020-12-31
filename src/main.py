import sys
import requests

name = 'DynamicSquid'#input('username: ')

website = requests.get('https://github.com/' + name)

if website.status_code == 404:
	print('user not found')
	sys.exit(1)

def extract_data(_page, tag, endTag, inc):
	website = requests.get('https://github.com/' + name + '/?tab=' + _page)
	page = website.content.decode('utf-8')

	tag_index = page.find(tag)
	while tag_index != -1:
		endIndex = page.find(endTag, tag_index + inc)
		print(' - ' + page[tag_index + inc : endIndex])
		tag_index = page.find(tag, tag_index + 1)

# bio

page = website.content.decode('utf-8')

bioIndex = page.find('f4"')
if page[bioIndex + 5] != 'h':
	endBio = page.find('<', bioIndex + 11)
	print(' - ' + page[bioIndex + 12 : endBio])

# location

extract_data('profile', 'Home location:', '"', 15)

# followers

print('\nfollowers:')

tag = 'k" data-hovercard-type="user" data-hovercard-url="/users/'
extract_data("followers", tag, "/", 57)

# following

print("\nfollowing:")

tag = 'k" data-hovercard-type="user" data-hovercard-url="/users/'
extract_data('following', tag, "/", 57)

# repos

print('\nrepos:')

tag = '<a href="/' + name + '/'
extract_data('repos', tag, '"', len(name) + 11)

# stars

print('\nstars:')

tag = 'd-inline-block mb-1'
extract_data('stars', tag, '"', 47)