/* Copyright (c) 2001-2016, The HSQL Development Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.hsqldb.persist;

import org.hsqldb.lib.LongLookup;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

/**
 * @author Fred Toussi (fredt@users dot sourceforge.net)
 * @version 2.3.3
 * @since 2.3.0
 */
public class DirectoryBlockCachedObject extends CachedObjectBase {

    public final static int fileSizeFactor = 12;

    //
    int[]  tableIds;
    int[]  bitmapAddress;
    char[] freeSpace;
    char[] freeSpaceBlock;

    public DirectoryBlockCachedObject(int capacity) {

        tableIds       = new int[capacity];
        bitmapAddress  = new int[capacity];
        freeSpace      = new char[capacity];
        freeSpaceBlock = new char[capacity];
        hasChanged     = true;
    }

    public void read(RowInputInterface in) {

        this.position = in.getFilePosition();

        int capacity = tableIds.length;

        for (int i = 0; i < capacity; i++) {
            tableIds[i] = in.readInt();
        }

        for (int i = 0; i < capacity; i++) {
            bitmapAddress[i] = in.readInt();
        }

        for (int i = 0; i < capacity; i++) {
            freeSpace[i] = in.readChar();
        }

        for (int i = 0; i < capacity; i++) {
            freeSpaceBlock[i] = in.readChar();
        }

        hasChanged = false;
    }

    public int getDefaultCapacity() {
        return tableIds.length;
    }

    public int getRealSize(RowOutputInterface out) {
        return tableIds.length * (PersistentStore.INT_STORE_SIZE * 3);
    }

    public void write(RowOutputInterface out) {
        write(out, null);
    }

    public void write(RowOutputInterface out, LongLookup lookup) {

        int capacity = tableIds.length;

        out.setStorageSize(storageSize);

        for (int i = 0; i < capacity; i++) {
            out.writeInt(tableIds[i]);
        }

        for (int i = 0; i < capacity; i++) {
            out.writeInt(bitmapAddress[i]);
        }

        for (int i = 0; i < capacity; i++) {
            out.writeChar(freeSpace[i]);
        }

        for (int i = 0; i < capacity; i++) {
            out.writeChar(freeSpaceBlock[i]);
        }

        out.writeEnd();
    }

    public int[] getTableIdArray() {
        return tableIds;
    }

    public int[] getBitmapAddressArray() {
        return bitmapAddress;
    }

    public char[] getFreeSpaceArray() {
        return freeSpace;
    }

    public char[] getFreeBlockArray() {
        return freeSpaceBlock;
    }
}
