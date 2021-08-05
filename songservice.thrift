
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

struct ArtistListSongResponse{
    1: int code
    2: list<Song> listSong
}

struct SongResponse{
    1: int code
    2: Song song
}

service SongService{

    SongResponse getSong(1: int id)
    int removeSong(1: int id)
    int updateSong(1: Song song)
    int addSong(1: Song song)

    int performLike(1: int songId)
    int performUnlike(1: int songId)

    int performIncreaseListen(1: int songId)

    ArtistListSongResponse getSongsByArtist(1: string artist)

    list<Song> getTopSongBaseOnLike()
    list<Song> getTopSongBaseOnListen()
}