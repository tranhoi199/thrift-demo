
typedef i32 int

struct Song{
    1: int id
    2: string title
    3: list<string> singer
}

struct Like{
    1: int id
    2: int Songid
    3: int numLike
}

struct Listen{
    1: int id
    2: int Songid
    3: int numListen
}

service SongService{

    Song getSong(1: int id)
    void removeSong(1: int id)
    void updateSong(1: Song song)

    int performLike(1: int songId)
    int performUnlike(1: int songId)

    int performIncreaseListen(1: int songId)
}