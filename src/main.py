import sys
import requests

def scrape(web_tab, tab):
  page = requests.get('https://github.com/' + web_tab)

  if page.status_code == 404:
    print('user not found')
    sys.exit(1)

  file = open('data/' + tab + '.txt', 'w')
  file.write(page.content.decode('utf-8'))
  file.close()

scrape(sys.argv[1], 'profile')
scrape(sys.argv[1] + '?tab=followers', 'followers')
scrape(sys.argv[1] + '?tab=following', 'following')
scrape(sys.argv[1] + '?tab=repositories', 'repos')
scrape(sys.argv[1] + '?tab=stars', 'stars')
